package com.adminsys.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BeanUtil;
import com.adminsys.dao.PaymentTransactionMapper;
import com.adminsys.dao.entity.PaymentTransactionEntity;
import com.adminsys.pay.output.PayMentTransacDTO;
import com.adminsys.service.PayMentTransacInfoService;
import com.adminsys.token.GenerateToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/16 上午11:36
 **/
@RestController
public class PayMentTransacInfoServiceImpl extends BaseApiService<PayMentTransacDTO> implements PayMentTransacInfoService {

    @Autowired
    private GenerateToken generateToken;
    @Autowired
    private PaymentTransactionMapper paymentTransactionMapper;

    @Override
    public BaseResponse<PayMentTransacDTO> tokenByPayMentTransac(String token) {
        // 1.验证token是否为空
        if (StringUtils.isEmpty(token)) {
            return setResultError("token参数不能空!");
        }
        // 2.使用token查询redisPayMentTransacID
        String value = generateToken.getToken(token);
        if (StringUtils.isEmpty(value)) {
            return setResultError("该Token可能已经失效或者已经过期");
        }
        long transactionId = Long.parseLong(value);
        // 4.使用transactionId查询支付信息
        PaymentTransactionEntity paymentTransaction = paymentTransactionMapper.selectById(transactionId);
        if (paymentTransaction == null) {
            return setResultError("未查询到该支付信息");
        }
        return setResultSuccess(BeanUtil.doToDto(paymentTransaction, PayMentTransacDTO.class));
    }
}
