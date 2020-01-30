package com.adminsys.callback.templete.alipay;

import com.adminsys.callback.templete.AbstractPayCallbackTemplate;
import com.adminsys.constant.PayConstant;
import com.adminsys.service.dao.PaymentTransactionMapper;
import com.adminsys.service.dao.entity.PaymentTransactionEntity;
import com.adminsys.mq.producer.IntegralProducer;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Qiang
 * @version 1.0
 * @description 支付宝异步回调具体实现
 * @date 2020/1/17 下午7:55
 **/
@Slf4j
@Component
public class AliPayCallbackTemplate extends AbstractPayCallbackTemplate {

    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Autowired
    private IntegralProducer integralProducer;

    @Override
    protected Map<String, String> verifySignature(HttpServletRequest request, HttpServletResponse resp) {
        log.info("BackRcvResponse接收后台通知开始");
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        try {
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }
            //调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            /* 实际验证过程建议商户务必添加以下校验：
                1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
                2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
                3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
                4、验证app_id是否为该商户本身。
	          */
            //验证成功
            if(signVerified) {
                params.put(PayConstant.RESULT_NAME, PayConstant.RESULT_PAYCODE_200);
            }else {//验证失败
                params.put(PayConstant.RESULT_NAME, PayConstant.RESULT_PAYCODE_201);
            }
        } catch (Exception e) {
            params.put(PayConstant.RESULT_NAME, PayConstant.RESULT_PAYCODE_201);
        }
        params.put("paymentId", requestParams.get("out_trade_no")[0]);
        params.put("trade_status", params.get("trade_status"));
        return params;
    }

    @Override
    public String asyncService(Map<String, String> verifySignature) {
        // 获取后台通知的数据，其他字段也可用类似方式获取  获取支付ID
        String orderId = verifySignature.get("out_trade_no");
        if(StringUtils.isEmpty(orderId)){
            return failResult();
        }
        // 1.根据orderId查询该支付信息
        PaymentTransactionEntity paymentTransaction = paymentTransactionMapper.selectByPaymentId(orderId);
        if (paymentTransaction == null) {
            return failResult();
        }
        // 2.判断之前是否已经支付过,已经支付过则返回ok
        Integer paymentStatus = paymentTransaction.getPaymentStatus();
        if (paymentStatus.equals(PayConstant.PAY_STATUS_SUCCESS)) {
            return successResult();
        }
        String respCode = verifySignature.get("trade_status");
        // 3.判断是否支付成功
        if (!("TRADE_FINISHED".equals(respCode) || "TRADE_SUCCESS".equals(respCode))) {
            return failResult();
        }
        // 修改交易订单状态为已支付(1)
        paymentTransactionMapper.updatePaymentStatus(PayConstant.PAY_STATUS_SUCCESS, orderId);
        // 3.调用积分服务接口增加积分(处理幂等性问题) MQ
        addMQIntegral(paymentTransaction);
        return successResult();
    }

    @Async
    public void addMQIntegral(PaymentTransactionEntity paymentTransaction) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paymentId", paymentTransaction.getPaymentId());
        jsonObject.put("userId", paymentTransaction.getUserId());
        jsonObject.put("integral", 100);
        integralProducer.send(jsonObject);
    }

    @Override
    public String failResult() {
        return PayConstant.YINLIAN_RESULT_FAIL;
    }

    @Override
    public String successResult() {
        return PayConstant.ALI_RESULT_SUCCESS;
    }
}
