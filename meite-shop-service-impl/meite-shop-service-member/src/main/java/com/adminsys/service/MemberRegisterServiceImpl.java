package com.adminsys.service;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.entity.UserEntity;
import com.adminsys.mapper.UserMapper;
import com.adminsys.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/23 下午6:19
 **/
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity, String registCode) {
        // 1 参数验证
        String userName = userEntity.getUserName();
        if(StringUtils.isEmpty(userName)){
            return setResultError("用户名称不能为空!");
        }
        String mobile = userEntity.getMobile();
        if(StringUtils.isEmpty(mobile)){
            return setResultError("手机号不能为空!");
        }
        String password = userEntity.getPassword();
        if(StringUtils.isEmpty(password)){
            return setResultError("密码不能为空!");
        }
        // 2 验证注册码是否正确
        // 3 对用户密码进行加密
        String newPwd = MD5Util.MD5(password);
        userEntity.setPassword(newPwd);
        // 4 调用数据库插入数据
        return userMapper.register(userEntity) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败");
    }
}
