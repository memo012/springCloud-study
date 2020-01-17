package com.adminsys.feign;

import com.adminsys.service.PaymentChannelService;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author memo012
 * 支付渠道客户端
 */
@FeignClient("app-pays")
public interface PaymentChannelFeign extends PaymentChannelService {

}
