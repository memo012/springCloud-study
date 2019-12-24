package com.adminsys.feign;

import com.adminsys.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/24 下午5:08
 **/
@FeignClient(name = "app-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {
}
