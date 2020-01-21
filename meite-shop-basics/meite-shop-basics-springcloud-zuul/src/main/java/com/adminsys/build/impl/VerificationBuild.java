package com.adminsys.build.impl;

import com.adminsys.build.GatewayBuild;
import com.adminsys.mapper.BlacklistMapper;
import com.adminsys.mapper.entity.BlackListDao;
import com.adminsys.sign.SignUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
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
        return null;
    }

    private void resultError(RequestContext ctx, String errorMsg) {
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);

    }

}
