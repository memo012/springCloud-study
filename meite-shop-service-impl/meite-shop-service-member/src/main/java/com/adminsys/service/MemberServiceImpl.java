package com.adminsys.service;

import com.adminsys.feign.WeiXinAppServiceFeign;
import com.adminsys.weixin.entity.AppEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Qiang
 * @version 1.0
 * @description 会员服务接口实现
 * @date 2019/12/4 下午6:25
 **/
@RestController
public class MemberServiceImpl implements MemberService {

    @Resource
    private WeiXinAppServiceFeign weiXinAppServiceFeign;

    @Override
    public AppEntity memberInvokeWeiXin() {
        // 会员调用微信
        return weiXinAppServiceFeign.getApp();
    }
}
