package com.adminsys.feign;

import com.adminsys.service.AuthorizationService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-auth")
public interface AuthorizationServiceFeign extends AuthorizationService {

}
