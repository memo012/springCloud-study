package com.adminsys.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.dao.PaymentChannelMapper;
import com.adminsys.dao.entity.PaymentChannelEntity;
import com.adminsys.factory.StrategyFactory;
import com.adminsys.pay.output.PayMentTransacDTO;
import com.adminsys.service.PayContextService;
import com.adminsys.service.PayMentTransacInfoService;
import com.adminsys.strategy.PayStrategy;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/16 下午3:49
 **/
@RestController
public class PayContextServiceImpl extends BaseApiService<JSONObject> implements PayContextService {

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;
    @Autowired
    private PayMentTransacInfoService payMentTransacInfoService;

    @Override
    public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken) {
        // 1.使用渠道id查询渠道信息
        PaymentChannelEntity pymentChannel = paymentChannelMapper.selectBychannelId(channelId);
        if (pymentChannel == null) {
            return setResultError("没有查询到该渠道信息");
        }
        // 2.使用payToken查询待支付信息
        BaseResponse<PayMentTransacDTO> tokenByPayMentTransac = payMentTransacInfoService
                .tokenByPayMentTransac(payToken);
        if (!isSuccess(tokenByPayMentTransac)) {
            return setResultSuccess(tokenByPayMentTransac.getMsg());
        }
        // 3.使用Java反射机制初始化子类
        String classAddres = pymentChannel.getClassAddres();
        PayStrategy payStrategy = StrategyFactory.getPayStrategy(classAddres);
        if (payStrategy == null) {
            return setResultError("支付系统网关错误!");
        }
        PayMentTransacDTO payMentTransacDTO = tokenByPayMentTransac.getData();
        // 4.直接执行子类实现方法
        String payHtml = payStrategy.toPayHtml(pymentChannel, payMentTransacDTO);
        JSONObject data = new JSONObject();
        data.put("payHtml", payHtml);
        return setResultSuccess(data);
    }
}
