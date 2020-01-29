package com.adminsys.handler;

import com.adminsys.handler.factory.FactoryHandler;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description 责任链模式执行
 * @date 2020/1/23 下午12:05
 **/
@Component
public class ResponsibilityClient {
    public void responsibility(RequestContext ctx, String ipAddres, HttpServletRequest request,
                               HttpServletResponse response) {
        GatewayHandler handler = FactoryHandler.getHandler();
        handler.service(ctx, ipAddres, request, response);
    }
}
