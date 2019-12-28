package com.adminsys.portal.feign;

import com.adminsys.service.MemberLoginService;
import com.adminsys.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/28 下午1:49
 **/
@FeignClient(name = "app-member")
public interface MemberLoginServiceFeign extends MemberLoginService {
}
