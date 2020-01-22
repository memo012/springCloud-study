package com.adminsys.build.impl;

import com.adminsys.base.BaseResponse;
import com.adminsys.build.GatewayBuild;
import com.adminsys.constants.Constants;
import com.adminsys.feign.AuthorizationServiceFeign;
import com.adminsys.mapper.BlacklistMapper;
import com.adminsys.mapper.entity.BlackListDao;
import com.adminsys.sign.SignUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Qiang
 * @version 1.0
 * @description 参数验证Build
 * @date 2020/1/21 下午5:52
 **/
@Slf4j
@Component
public class VerificationBuild implements GatewayBuild {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private AuthorizationServiceFeign authorizationServiceFeign;

    @Override
    public Boolean blackBlock(RequestContext ctx, String ipAddress, HttpServletResponse response) {
        // 2.查询数据库黑名单
        BlackListDao blacklist = blacklistMapper.findBlacklist(ipAddress);
        if (blacklist != null) {
            resultError(ctx, "ip:" + ipAddress + ",Insufficient access rights");
            return false;
        }
        return true;
    }

    @Override
    public Boolean toVerifyMap(RequestContext ctx, String ipAddress, HttpServletRequest request) {
         // 4.验证签名拦截
        Map<String, String> verifyMap =
                SignUtil.toVerifyMap(request.getParameterMap(), false);
        if (!SignUtil.verify(verifyMap)) {
            resultError(ctx, "ip:" + ipAddress + ",Sign fail");
            return false;
        }
        return true;
    }

    @Override
    public Boolean apiAuthority(RequestContext ctx, HttpServletRequest request) {
        String servletPath = request.getServletPath();
        log.info(">>>>>servletPath:" + servletPath + ",servletPath.substring(0, 5):" + servletPath.substring(0, 5));
        if (!servletPath.substring(0, 7).equals("/public")) {
            return true;
        }
        String accessToken = request.getParameter("accessToken");
        log.info(">>>>>accessToken验证:" + accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            resultError(ctx, "AccessToken cannot be empty");
            return false;
        }
        // 调用接口验证accessToken是否失效
        BaseResponse<JSONObject> appInfo = authorizationServiceFeign.getAppInfo(accessToken);
        log.info(">>>>>>data:" + appInfo.toString());
        if (!isSuccess(appInfo)) {
            resultError(ctx, appInfo.getMsg());
            return false;
        }
        return true;
    }

    // 接口直接返回true 或者false
    public Boolean isSuccess(BaseResponse<?> baseResp) {
        if (baseResp == null) {
            return false;
        }
        if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return false;
        }
        return true;
    }


    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);

    }

}
