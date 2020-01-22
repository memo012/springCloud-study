package com.adminsys.file;

import com.adminsys.build.GatewayDirector;
import com.adminsys.mapper.BlacklistMapper;
import com.adminsys.mapper.entity.BlackListDao;
import com.adminsys.sign.SignUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/20 下午7:53
 **/
@Component
@Slf4j
public class GatewayFilter extends ZuulFilter {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private GatewayDirector gatewayDirector;

    /**
     * 在方法之前拦截
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 1. 获取请求对象
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        // 2. 获取客户端真实ip
        String ipAddr = getIpAddress(request);
        gatewayDirector.director(ctx, ipAddr, request, response);
        // 5.防止xss攻击
        ctx.setRequestQueryParams(filterParameters(request, ctx));
        return null;
    }

    /**
     * 过滤参数
     */
    private Map<String, List<String>> filterParameters(HttpServletRequest request, RequestContext ctx) {
        Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
        if (requestQueryParams == null) {
            requestQueryParams = new HashMap<>();
        }
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            ArrayList<String> arrayList = new ArrayList<>();
            // 将参数转化为html参数 防止xss攻击
            arrayList.add(StringEscapeUtils.escapeHtml(value));
            requestQueryParams.put(name, arrayList);
        }
        return requestQueryParams;
    }


    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String xip = request.getHeader("X-Real-IP");
        if (ip != null && ip.length() != 0 && !ip.equalsIgnoreCase("unKnown")) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = xip;
        if (ip != null && ip.length() != 0 && !ip.equalsIgnoreCase("unKnown")) {
            return ip;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
