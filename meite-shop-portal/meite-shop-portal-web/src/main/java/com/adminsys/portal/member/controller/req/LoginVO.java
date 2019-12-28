package com.adminsys.portal.member.controller.req;

import lombok.Data;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/28 下午1:01
 **/
@Data
public class LoginVO {

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 手机密码
     */
    private String password;
    /**
     * 图形验证码
     */
    private String graphicCode;


}
