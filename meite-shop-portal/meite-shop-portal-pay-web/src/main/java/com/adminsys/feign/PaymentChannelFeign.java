package com.adminsys.feign;

import com.adminsys.service.PaymentChannelService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-pays")
public interface PaymentChannelFeign extends PaymentChannelService {

}
