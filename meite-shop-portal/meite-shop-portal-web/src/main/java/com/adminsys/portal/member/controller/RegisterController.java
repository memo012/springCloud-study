package com.adminsys.portal.member.controller;

import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BaseWebController;
import com.adminsys.constants.Constants;
import com.adminsys.member.input.dto.UserInputDTO;
import com.adminsys.portal.feign.MemberRegisterServiceFeign;
import com.adminsys.portal.member.controller.req.RegisterVO;
import com.adminsys.utils.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Qiang
 * @version 1.0
 * @description 会员注册
 * @date 2019/12/28 下午1:02
 **/
@Controller
@Slf4j
public class RegisterController extends BaseWebController {

    @Autowired
    private MemberRegisterServiceFeign memberRegisterServiceFeign;

    private static final String ME_REGISTER_PAGE = "member/register";

    /**
     * 跳转到登陆页面页面
     */
    private static final String ME_LOGIN_FTL = "member/login";

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String getRegister() {
        return ME_REGISTER_PAGE;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerVo") @Validated RegisterVO registerVo, BindingResult bindingResult, Model model) {
        // 1.参数验证
        if (bindingResult.hasErrors()) {
            // 获取第一个错误!
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            setErrorMsg(model, errorMsg);
            return ME_REGISTER_PAGE;
        }
        // 将VO转换DTO
        UserInputDTO userInputDTO = BeanUtil.voToDto(registerVo, UserInputDTO.class);
        String registCode = registerVo.getRegistCode();
        try {
            BaseResponse<JSONObject> register = memberRegisterServiceFeign.register(userInputDTO, registCode);
            if(!register.getCode().equals(Constants.HTTP_RES_CODE_200)){
                model.addAttribute("error", register.getMsg());
                return ME_REGISTER_PAGE;
            }
        }catch (Exception e){
            log.error(">>>>>", e);
            model.addAttribute("error", "系统出现错误!");
            return ME_REGISTER_PAGE;
        }

        return ME_LOGIN_FTL;
    }

}
