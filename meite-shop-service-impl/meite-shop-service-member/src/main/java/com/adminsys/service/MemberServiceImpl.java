package com.adminsys.service;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BeanUtil;
import com.adminsys.constants.Constants;
import com.adminsys.mapper.UserMapper;
import com.adminsys.mapper.entity.UserDo;
import com.adminsys.member.output.dto.UserOutDTO;
import com.adminsys.token.GenerateToken;
import com.adminsys.type.TypeCastHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description 会员服务接口实现
 * @date 2019/12/4 下午6:25
 **/
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDTO> implements MemberService {

//    @Resource
//    private WeiXinAppServiceFeign weiXinAppServiceFeign;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        // 1 验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号不能为空!");
        }
        // 2 根据手机号查询信息 单独定义code
        UserDo userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_NOTUSER_203, "用户信息不存在");
        }
        // 将do变成dto
        UserOutDTO userOutDTO = BeanUtil.doToDto(userEntity, UserOutDTO.class);
        return setResultSuccess(userOutDTO);
    }

    @Override
    public BaseResponse<UserOutDTO> getInfo(String token) {
        // 1.参数验证
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token向redis中查询userId
        String redisValue = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisValue)) {
            return setResultError("token已经失效或者不正确");
        }
        Long userId = TypeCastHelper.toLong(redisValue);
        // 3.根据userId查询用户信息
        UserDo userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户信息不存在!");
        }
        // 4.将Do转换为Dto
        UserOutDTO doToDto = BeanUtil.doToDto(userDo, UserOutDTO.class);
        return setResultSuccess(doToDto);
    }
}
