package com.adminsys.service.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.service.VerificaCodeService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/24 下午5:19
 **/
@RestController
public class VerificaCodeServiceImpl extends BaseApiService<JSONObject> implements VerificaCodeService {
    @Override
    public BaseResponse<JSONObject> verificaWeixinCode(String phone, String weixinCode) {
        if (StringUtils.isEmpty(phone)) {
            return setResultError("手机号码不能为空!");
        }
        if (StringUtils.isEmpty(weixinCode)) {
            return setResultError("注册码不能为空!");
        }
        String code = "1234";
        if (StringUtils.isEmpty(code)) {
            return setResultError("注册码已经过期,请重新发送验证码");
        }
        if (!code.equals(weixinCode)) {
            return setResultError("注册码不正确");
        }
        return setResultSuccess("注册码验证码正确");
    }
}
