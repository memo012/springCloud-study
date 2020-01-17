package com.adminsys.feign;

import com.adminsys.service.PayContextService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author memo012
 * 支付渠道客户端
 */
@FeignClient("app-pays")
public interface PayContextFeign extends PayContextService {

}
