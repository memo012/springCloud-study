package com.adminsys.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2019/12/4 下午1:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppEntity {

    /**
     *  appid
     */
    private String appId;

    /**
     *  应用名称
     */
    private String appName;

}
