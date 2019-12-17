package com.adminsys.service;

import com.adminsys.weixin.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description 会员服务接口
 * @date 2019/12/4 下午1:18
 **/
@Api(tags = "会员服务接口")
public interface MemberService {

    /**
     * 会员服务接口调用微信接口
     * @return
     */
    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberInvokeWeiXin")
    public AppEntity memberInvokeWeiXin();

}
