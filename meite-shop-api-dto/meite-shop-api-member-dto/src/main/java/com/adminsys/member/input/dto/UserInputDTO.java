package com.adminsys.member.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Qiang
 * @version 1.0
 * @description 用户信息实体类
 * @date 2019/12/23 下午6:09
 **/
@Data
@ApiModel(value = "用户信息实体类")
public class UserInputDTO implements Serializable {

    /**
     * userid
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    /**
     * 性别 0 男 1女
     */
    @ApiModelProperty(value = "用户性别")
    private char sex;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = " 用户头像")
    private String picImg;
    /**
     * 用户关联 QQ 开放ID
     */
    @ApiModelProperty(value = "用户关联 QQ 开放ID")
    private String qqOpenId;
    /**
     * 用户关联 微信 开放ID
     */
    @ApiModelProperty(value = "用户关联 微信 开放ID")
    private String WxOpenId;

}
