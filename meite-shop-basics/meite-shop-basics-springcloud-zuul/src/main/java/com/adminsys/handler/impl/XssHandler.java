package com.adminsys.handler.impl;

import com.adminsys.handler.GatewayHandler;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Qiang
 * @version 1.0
 * @description xss攻击
 * @date 2020/1/23 下午12:07
 **/
@Component
public class XssHandler extends BaseHandler implements GatewayHandler {
    @Override
    public void service(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response) {
        ctx.setRequestQueryParams(filterParameters(request, ctx));
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

}
