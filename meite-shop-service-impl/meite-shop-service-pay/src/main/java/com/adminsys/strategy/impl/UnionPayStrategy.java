package com.adminsys.strategy.impl;

import com.adminsys.service.dao.entity.PaymentChannelEntity;
import com.adminsys.pay.output.PayMentTransacDTO;
import com.adminsys.strategy.PayStrategy;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/16 下午3:43
 **/
public class UnionPayStrategy implements PayStrategy {
    @Override
    public String toPayHtml(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO) {
        return "银联支付";
    }
}
