package com.adminsys.controller;

import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BaseWebController;
import com.adminsys.feign.PayMentTransacInfoFeign;
import com.adminsys.feign.PaymentChannelFeign;
import com.adminsys.pay.output.PayMentTransacDTO;
import com.adminsys.pay.output.PaymentChannelDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/4 下午5:15
 **/
@Controller
public class PayController extends BaseWebController {

    @Autowired
    private PayMentTransacInfoFeign payMentTransacInfoFeign;
    @Autowired
    private PaymentChannelFeign paymentChannelFeign;

    @RequestMapping("/pay")
    public String pay(String payToken, Model model) {
    // 1.验证payToken参数
        if (StringUtils.isEmpty(payToken)) {
            setErrorMsg(model, "支付令牌不能为空!");
            return ERROR_500_FTL;
        }
        // 2.使用payToken查询支付信息
        BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoFeign.tokenByPayMentTransac(payToken);
        if (!isSuccess(tokenByPayMentTransac)) {
            setErrorMsg(model, tokenByPayMentTransac.getMsg());
            return ERROR_500_FTL;
        }
        // 3.查询支付信息
        PayMentTransacDTO data = tokenByPayMentTransac.getData();
        model.addAttribute("data", data);
        // 4.查询渠道信息
        List<PaymentChannelDTO> paymentChanneList = paymentChannelFeign.selectAll();
        model.addAttribute("paymentChanneList", paymentChanneList);
        return "index";
    }

}
