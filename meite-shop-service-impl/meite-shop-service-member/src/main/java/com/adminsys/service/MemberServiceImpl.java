package com.adminsys.service;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BeanUtil;
import com.adminsys.constants.Constants;
import com.adminsys.mapper.UserMapper;
import com.adminsys.mapper.entity.UserDo;
import com.adminsys.member.output.dto.UserOutDTO;
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
}
