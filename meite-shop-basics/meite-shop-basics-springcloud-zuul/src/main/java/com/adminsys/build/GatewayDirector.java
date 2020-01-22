package com.adminsys.build;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description 链接Build
 * @date 2020/1/21 下午5:56
 **/
@Component
public class GatewayDirector {

    @Resource(name = "verificationBuild")
    private GatewayBuild gatewayBuild;

    public void director(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 黑名单拦截
         */
        Boolean blackBlock = gatewayBuild.blackBlock(ctx, ipAddres, response);
        if (!blackBlock) {
            return;
        }
        /**
         * 参数验证
         */
//        Boolean verifyMap = gatewayBuild.toVerifyMap(ctx, ipAddres, request);
//        if (!verifyMap) {
//            return;
//        }
        // 3.验证accessToken
        Boolean apiAuthority = gatewayBuild.apiAuthority(ctx, request);
        if (!apiAuthority) {
            return;
        }
    }


}
