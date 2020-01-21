package com.adminsys.build;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description 网关权限Build
 * @date 2020/1/21 下午5:49
 **/
public interface GatewayBuild {

    /**
     * 黑名单拦截
     * @param ctx
     * @param ipAddress
     * @param response
     * @return
     */
    Boolean blackBlock(RequestContext ctx, String ipAddress, HttpServletResponse response);


    /**
     *  参数验证
     * @param ctx
     * @param ipAddress
     * @param request
     * @return
     */
    Boolean toVerifyMap(RequestContext ctx, String ipAddress, HttpServletRequest request);


    /**
     * api权限控制
     * @param ctx
     * @param request
     * @return
     */
    Boolean apiAuthority(RequestContext ctx, HttpServletRequest request);

}
