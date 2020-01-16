package com.adminsys.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.dao.PaymentChannelMapper;
import com.adminsys.dao.entity.PaymentChannelEntity;
import com.adminsys.mapper.MapperUtils;
import com.adminsys.pay.output.PaymentChannelDTO;
import com.adminsys.service.PaymentChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/16 下午2:28
 **/
@RestController
public class PaymentChannelServiceImpl extends BaseApiService<List<PaymentChannelDTO>> implements PaymentChannelService  {

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    @Override
    public List<PaymentChannelDTO> selectAll() {
        List<PaymentChannelEntity> paymentChanneList = paymentChannelMapper.selectAll();
        return MapperUtils.mapAsList(paymentChanneList, PaymentChannelDTO.class);
    }
}
