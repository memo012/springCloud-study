package com.adminsys.handler.impl;

import com.adminsys.handler.GatewayHandler;
import com.adminsys.mapper.BlacklistMapper;
import com.adminsys.mapper.entity.BlackListDao;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/23 上午11:54
 **/
@Component
public class BlacklistHandler extends BaseHandler implements GatewayHandler {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public void service(RequestContext ctx, String ipAddress, HttpServletRequest request, HttpServletResponse response) {
        // 2.查询数据库黑名单
        BlackListDao blacklist = blacklistMapper.findBlacklist(ipAddress);
        if (blacklist != null) {
            resultError(ctx, "ip:" + ipAddress + ",Insufficient access rights");
            return;
        }
        // 执行下一个流程
        nextGatewayHandler.service(ctx, ipAddress, request, response);
    }
}
