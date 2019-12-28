package com.adminsys.portal.controller;

import com.adminsys.base.BaseResponse;
import com.adminsys.member.output.dto.UserOutDTO;
import com.adminsys.portal.feign.MemberServiceFeign;
import com.adminsys.portal.web.constants.WebConstants;
import com.adminsys.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Qiang
 * @version 1.0
 * @description 首页controller
 * @date 2019/12/27 下午7:32
 **/
@Controller
public class IndexController {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /**
     * 跳转到首页
     */
    private static final String INDEX_FIL = "index";

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 1.从cookie 中 获取 会员token
        String token = CookieUtils.getCookieValue(request, WebConstants.LOGIN_TOKEN_COOKIENAME, true);
        if (!StringUtils.isEmpty(token)) {
            // 2.调用会员服务接口,查询会员用户信息
            BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getInfo(token);
            UserOutDTO data = userInfo.getData();
            if (data != null) {
                String mobile = data.getMobile();
                // 对手机号码实现脱敏
                String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                model.addAttribute("desensMobile", desensMobile);
            }
        }
        return INDEX_FIL;
    }

}
