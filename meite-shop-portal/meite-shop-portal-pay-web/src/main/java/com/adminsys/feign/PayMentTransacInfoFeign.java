package com.adminsys.feign;

import com.adminsys.service.PayMentTransacInfoService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-pays")
public interface PayMentTransacInfoFeign extends PayMentTransacInfoService {

}
