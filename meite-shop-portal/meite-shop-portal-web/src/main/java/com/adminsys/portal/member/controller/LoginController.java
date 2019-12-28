package com.adminsys.portal.member.controller;

import com.adminsys.base.BaseResponse;
import com.adminsys.bean.BaseWebController;
import com.adminsys.constants.Constants;
import com.adminsys.member.input.dto.UserLoginInpDTO;
import com.adminsys.portal.code.RandomValidateCodeUtil;
import com.adminsys.portal.feign.MemberLoginServiceFeign;
import com.adminsys.portal.member.controller.req.LoginVO;
import com.adminsys.portal.web.constants.WebConstants;
import com.adminsys.utils.BeanUtil;
import com.adminsys.utils.CookieUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Qiang
 * @version 1.0
 * @description 登录controller
 * @date 2019/12/27 下午7:34
 **/
@Controller
public class LoginController extends BaseWebController {

    @Autowired
    private MemberLoginServiceFeign memberLoginServiceFeign;

    private static final String MB_LOGIN_PAGE = "member/login";

    /**
     * 重定向到首页
     */
    private static final String REDIRECT_INDEX = "redirect:/";

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("/login")
    public String getLogin() {
        return MB_LOGIN_PAGE;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("loginVo") LoginVO loginVo, Model model, HttpSession httpSession, HttpServletResponse response, HttpServletRequest request) {
        // 1.图形验证码判断
        String graphicCode = loginVo.getGraphicCode();
        if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
            setErrorMsg(model, "图形验证码不正确!");
            return MB_LOGIN_PAGE;
        }
        // 2.将vo转换为dto
        UserLoginInpDTO voToDto = BeanUtil.voToDto(loginVo, UserLoginInpDTO.class);
        voToDto.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
        String info = webBrowserInfo(request);
        voToDto.setDeviceInfor(info);
        BaseResponse<JSONObject> login = memberLoginServiceFeign.login(voToDto);
        if (!isSuccess(login)) {
            setErrorMsg(model, login.getMsg());
            return MB_LOGIN_PAGE;
        }
         // 2.将token存入到cookie中
        JSONObject data = login.getData();
        String token = data.getString("token");
        CookieUtils.setCookie(request, response, WebConstants.LOGIN_TOKEN_COOKIENAME, token);
        // 登陆成功后,跳转到首页
        return REDIRECT_INDEX;
    }

}
