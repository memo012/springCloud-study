package com.adminsys.feign;

import com.adminsys.service.WeiXinAppService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/4 下午6:33
 **/
@FeignClient(name = "app-weixin")
public interface WeiXinAppServiceFeign extends WeiXinAppService {

}
