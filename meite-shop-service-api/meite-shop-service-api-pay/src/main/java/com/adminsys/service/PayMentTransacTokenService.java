package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.adminsys.pay.input.PayCratePayTokenDto;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/14 上午10:34
 **/
@Api(tags = "支付服务接口")
public interface PayMentTransacTokenService {

    /**
     * 创建支付令牌
     * @param payCratePayTokenDto
     * @return
     */
    @GetMapping("/cratePayToken")
    @ApiOperation(value = "支付令牌接口")
    BaseResponse<JSONObject> cratePayToken(@Validated PayCratePayTokenDto payCratePayTokenDto);

}
