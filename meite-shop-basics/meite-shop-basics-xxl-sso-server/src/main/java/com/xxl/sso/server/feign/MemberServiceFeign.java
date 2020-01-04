package com.xxl.sso.server.feign;

import com.adminsys.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/4 下午2:47
 **/
@FeignClient("app-member")
public interface MemberServiceFeign extends MemberService {
}
