package com.adminsys.handler.impl;

import com.adminsys.base.BaseResponse;
import com.adminsys.feign.AuthorizationServiceFeign;
import com.adminsys.handler.GatewayHandler;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/23 上午11:59
 **/
@Component
@Slf4j
public class ApiAuthorityHandler extends BaseHandler implements GatewayHandler {

    @Autowired
    private AuthorizationServiceFeign authorizationServiceFeign;

    @Override
    public void service(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response) {
        String servletPath = request.getServletPath();
        log.info(">>>>>servletPath:" + servletPath + ",servletPath.substring(0, 5):" + servletPath.substring(0, 5));
        if (!servletPath.substring(0, 7).equals("/public")) {
            nextGatewayHandler.service(ctx, ipAddres, request, response);
            return;
        }
        String accessToken = request.getParameter("accessToken");
        log.info(">>>>>accessToken验证:" + accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            resultError(ctx, "AccessToken cannot be empty");
            return;
        }
        // 调用接口验证accessToken是否失效
        BaseResponse<JSONObject> appInfo = authorizationServiceFeign.getAppInfo(accessToken);
        log.info(">>>>>>data:" + appInfo.toString());
        if (!isSuccess(appInfo)) {
            resultError(ctx, appInfo.getMsg());
            return;
        }
        nextGatewayHandler.service(ctx, ipAddres, request, response);
    }
}
