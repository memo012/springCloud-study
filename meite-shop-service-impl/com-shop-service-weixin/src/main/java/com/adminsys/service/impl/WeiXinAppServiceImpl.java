package com.adminsys.service.impl;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.service.WeiXinAppService;
import com.adminsys.weixin.entity.AppEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description 微信服务接口实现
 * @date 2019/12/4 下午1:34
 **/
@RestController
public class WeiXinAppServiceImpl extends BaseApiService<AppEntity> implements WeiXinAppService {


    @Override
    public BaseResponse<AppEntity> getApp() {
        return setResultSuccess(new AppEntity("123456", "memo012"));
    }
}
