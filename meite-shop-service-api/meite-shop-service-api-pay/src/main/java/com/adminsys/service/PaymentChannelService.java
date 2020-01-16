package com.adminsys.service;

import com.adminsys.pay.output.PaymentChannelDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/16 下午2:27
 **/
public interface PaymentChannelService {

    /**
     * 查询所有支付渠道
     *
     * @return
     */
    @GetMapping("/selectAll")
    public List<PaymentChannelDTO> selectAll();

}
