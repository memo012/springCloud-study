package com.adminsys.service;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.constants.Constants;
import com.adminsys.mapper.UserMapper;
import com.adminsys.mapper.UserTokenMapper;
import com.adminsys.mapper.entity.UserDo;
import com.adminsys.mapper.entity.UserTokenDo;
import com.adminsys.member.input.dto.UserLoginInpDTO;
import com.adminsys.token.GenerateToken;
import com.adminsys.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/26 上午9:32
 **/
@RestController
public class MemberLoginServiceImpl extends BaseApiService<JSONObject> implements MemberLoginService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO) {
        // 1.验证参数
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserDo userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }
        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        // 3.查询之前是否有过登陆
        Long userId = userDo.getUserId();
        UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
        if (userTokenDo != null) {
            // 4.清除之前的token
            String token = userTokenDo.getToken();
            Boolean removeToken = generateToken.removeToken(token);
            if (removeToken) {
                // 将该token的状态改为1
                userTokenMapper.updateTokenAvailability(token);
            }
        }
        // 5. 生成新的token
        String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
        String token = generateToken.createToken(keyPrefix, userId + "",
                Constants.MEMBRE_LOGIN_TOKEN_TIME);
        JSONObject tokenData = new JSONObject();
        tokenData.put("token", token);
        // 6. 存入在数据库中
        UserTokenDo userToken = new UserTokenDo();
        userToken.setUserId(userId);
        userToken.setLoginType(userLoginInpDTO.getLoginType());
        userToken.setToken(token);
        userToken.setDeviceInfor(deviceInfor);
        userTokenMapper.insertUserToken(userToken);
        return setResultSuccess(tokenData);
    }


}
