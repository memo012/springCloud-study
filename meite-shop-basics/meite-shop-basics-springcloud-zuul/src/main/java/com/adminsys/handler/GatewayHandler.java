package com.adminsys.handler;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description 网关处理接口
 * @date 2020/1/23 上午11:52
 **/
public interface GatewayHandler {


    /**
     * 网关拦截处理请求
     */
    void service(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response);

    /**
     * 设置下一个
     */
    void setNextHandler(GatewayHandler gatewayHandler);

}
