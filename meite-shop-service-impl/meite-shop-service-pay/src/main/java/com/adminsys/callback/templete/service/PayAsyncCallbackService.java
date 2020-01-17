package com.adminsys.callback.templete.service;

import com.adminsys.callback.enums.PayTemplateName;
import com.adminsys.callback.factory.TemplatesFactory;
import com.adminsys.callback.templete.AbstractPayCallbackTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/17 下午8:50
 **/
@RestController
public class PayAsyncCallbackService {

    /**
     * 支付宝异步回调接口执行代码
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/aliPayAsyncCallback")
    public String aliPayAsyncCallback(HttpServletRequest req, HttpServletResponse resp) {
        AbstractPayCallbackTemplate payCallbackTemplate = TemplatesFactory.getPayCallbackTemplate(PayTemplateName.ALI_PAY_TEMPLATE.getTemplateName());
        return payCallbackTemplate.asyncCallBack(req, resp);
    }
}
