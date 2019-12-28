package com.adminsys.portal.feign;

import com.adminsys.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author memo012
 */
@FeignClient("app-member")
public interface MemberServiceFeign extends MemberService {

}
