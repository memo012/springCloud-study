package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.adminsys.entity.UserEntity;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qiang
 * 会员注册接口
 */
@Api(tags = "会员注册接口")
public interface MemberRegisterService {

    /**
     * 用户注册接口
     *
     * @param userEntity
     * @param registCode
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity,
                                      @RequestParam("registCode") String registCode);

}