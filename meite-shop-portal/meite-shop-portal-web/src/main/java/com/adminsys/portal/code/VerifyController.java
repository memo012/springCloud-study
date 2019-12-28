package com.adminsys.portal.code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author memo012
 *  创建图形验证码工具类
 */
@Controller
public class VerifyController {
	/**
	 * 生成验证码
	 */
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			// 输出验证码图
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e) {

		}
	}
}