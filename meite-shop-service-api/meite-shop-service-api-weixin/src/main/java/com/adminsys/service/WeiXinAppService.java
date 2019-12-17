package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.adminsys.weixin.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description 微信服务接口
 * @date 2019/12/4 下午1:18
 **/
@Api(tags = "微信服务接口")
public interface WeiXinAppService {

    /**
     * 功能说明接口： 应用服务接口
     * @return 实体
     */
    @ApiOperation(value = "微信应用服务接口")
    @GetMapping("/getApp")
    public BaseResponse<AppEntity> getApp();
}
