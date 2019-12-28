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
import com.adminsys.transaction.RedisDataSourceTransaction;
import com.adminsys.utils.MD5Util;
import com.adminsys.utils.TimeUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * 手动事务工具类
     */
    @Resource
    private RedisDataSourceTransaction manualTransaction;

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
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        // 目的是限制范围
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }
        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        // 2.用户名称与密码登陆
        UserDo userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }
        TransactionStatus transactionStatus = null;
        try {
            // 1. 获取用户userId
            Long userId = userDo.getUserId();
            // 3. 根据userId+loginType 查询当前登陆类型账号之前是否有登陆过，如果登陆过 清除之前redistoken
            UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
            // 手动开启redis和数据库事物管理
            transactionStatus = manualTransaction.begin();
            if (userTokenDo != null) {
                // 如果登陆过 清除之前redistoken
                String oldToken = userTokenDo.getToken();
                generateToken.removeToken(oldToken);
                int updateTokenAvailability = userTokenMapper.updateTokenAvailability(oldToken);
                if (updateTokenAvailability < 0) {
                    manualTransaction.rollback(transactionStatus);
                    return setResultError("系统错误");
                }
            }
            // 4.将用户生成的令牌插入到Token记录表中
            String keyPrefix = Constants.MEMBER_TOKEN_KEYPREFIX + loginType;
            String newToken = generateToken.createToken(keyPrefix, userId + "",
                    Constants.MEMBRE_LOGIN_TOKEN_TIME);
            UserTokenDo userToken = new UserTokenDo();
            userToken.setId(Long.parseLong(new TimeUtil().getLongTime()));
            userToken.setUserId(userId);
            userToken.setLoginType(userLoginInpDTO.getLoginType());
            userToken.setToken(newToken);
            userToken.setDeviceInfor(deviceInfor);
            int result = userTokenMapper.insertUserToken(userToken);
            if (!toDaoResult(result)) {
                manualTransaction.rollback(transactionStatus);
                return setResultError("系统错误!");
            }
            // #######提交事务
            JSONObject data = new JSONObject();
            data.put("token", newToken);
            manualTransaction.commit(transactionStatus);
            return setResultSuccess(data);
        } catch (Exception e) {
            // 回滚事务
            try {
                manualTransaction.rollback(transactionStatus);
            } catch (Exception ex) {
            }
            return setResultError("系统错误!");
        }
    }

}
