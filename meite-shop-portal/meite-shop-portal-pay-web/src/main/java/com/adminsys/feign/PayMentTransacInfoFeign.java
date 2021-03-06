package com.adminsys.feign;

import com.adminsys.service.PayMentTransacInfoService;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author memo012
 * 支付渠道客户端
 */
@FeignClient("app-pays")
public interface PayMentTransacInfoFeign extends PayMentTransacInfoService {

}
