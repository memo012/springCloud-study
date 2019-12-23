package com.adminsys.feign;

import com.adminsys.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/23 下午7:31
 **/
@FeignClient(name = "app-member")
public interface MemberServiceFeign extends MemberService {
}
