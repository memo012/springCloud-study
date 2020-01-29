package com.adminsys.handler.factory;

import com.adminsys.handler.GatewayHandler;
import com.adminsys.handler.impl.XssHandler;
import com.adminsys.utils.SpringContextUtil;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/23 下午12:03
 **/
public class FactoryHandler {
    public static GatewayHandler getHandler() {
        // 1.黑名单拦截
        GatewayHandler handler1 = (GatewayHandler) SpringContextUtil.getBean("blacklistHandler");
        // 2.验证accessToken
        GatewayHandler handler2 = (GatewayHandler) SpringContextUtil.getBean("apiAuthorityHandler");
        handler1.setNextHandler(handler2);
        XssHandler handler3 =  (XssHandler)SpringContextUtil.getBean("xssHandler");
        handler2.setNextHandler(handler3);
        return handler1;
    }
}
