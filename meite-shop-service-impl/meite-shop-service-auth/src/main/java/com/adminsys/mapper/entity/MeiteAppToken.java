package com.adminsys.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/22 下午5:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeiteAppToken implements Serializable {

    /**
     *  主键
     */
    private Integer id;

    /**
     *  机构名称
     */
    private String appName;

    /**
     *  机构ID
     */
    private String appId;

    /**
     *  获取通行token
     */
    private String appToken;

    /**
     * 0 -- 可用  1 -- 不可用
     */
    private Integer isVerify;

    /**
     * 创建时间
     */
    private Date createTime;

    public MeiteAppToken(String appName, String appId, String appToken){
        this.appName = appName;
        this.appId = appId;
        this.appToken = appToken;
    }



}
