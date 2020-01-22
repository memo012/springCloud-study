package com.adminsys.service;

import com.adminsys.base.BaseApiService;
import com.adminsys.base.BaseResponse;
import com.adminsys.mapper.APPTokenMapper;
import com.adminsys.mapper.AppInfoMapper;
import com.adminsys.mapper.entity.MeiteAppInfo;
import com.adminsys.mapper.entity.MeiteAppToken;
import com.adminsys.token.GenerateToken;
import com.adminsys.utils.Guid;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/22 下午4:58
 **/
@RestController
public class AuthorizationServiceImpl extends BaseApiService<JSONObject> implements AuthorizationService {

    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private APPTokenMapper appTokenMapper;


    @Override
    public BaseResponse<JSONObject> applyAppInfo(String appName) {
        // 1.验证参数
        if (StringUtils.isEmpty(appName)) {
            return setResultError("机构名称不能为空!");
        }
        // 2.生成appid和appScrec
        Guid guid = new Guid();
        String appId = guid.getAppId();
        String appScrect = guid.getAppScrect();
        // 3.添加数据库中
        MeiteAppInfo meiteAppInfo = new MeiteAppInfo(appName, appId, appScrect);
        int insertAppInfo = appInfoMapper.insertAppInfo(meiteAppInfo);
        if (!toDaoResult(insertAppInfo)) {
            return setResultError("申请失败!");
        }
        // 4.返回给客户端
        JSONObject data = new JSONObject();
        data.put("appId", appId);
        data.put("appScrect", appScrect);
        return setResultSuccess(data);
    }

    @Override
    public BaseResponse<JSONObject> getAccessToken(String appId, String appSecret) {
        // 使用appid+appSecret获取AccessToken
        // 1.参数验证
        if (StringUtils.isEmpty(appId)) {
            return setResultError("appId不能为空!");
        }
        if (StringUtils.isEmpty(appSecret)) {
            return setResultError("appSecret不能为空!");
        }
        // 2.使用appId+appSecret查询数据库
        MeiteAppInfo meiteAppInfo = appInfoMapper.selectByAppInfo(appId, appSecret);
        if (meiteAppInfo == null) {
            return setResultError("appId或者是appSecret错误");
        }
        // 3. 查询先前是否已生成accessToken
        MeiteAppToken meiteAppToken = appTokenMapper.selectAccessTokenByAppId(meiteAppInfo.getAppId());
        if(meiteAppToken != null){
            // 设计该token为不可用状态 并且删除redis中数据
            appTokenMapper.updTokenStatus(meiteAppInfo.getAppId());
            generateToken.removeToken(meiteAppToken.getAppToken());
        }
        // 4.获取应用机构信息 生成accessToken
        String dbAppId = meiteAppInfo.getAppId();
        String accessToken = generateToken.createToken("auth", dbAppId);
        appTokenMapper.insertAppToken(new MeiteAppToken(meiteAppInfo.getAppName(), meiteAppInfo.getAppId(), accessToken));
        JSONObject data = new JSONObject();
        data.put("accessToken", accessToken);
        return setResultSuccess(data);
    }

    @Override
    public BaseResponse<JSONObject> getAppInfo(String accessToken) {
        // 1.验证参数
        if (StringUtils.isEmpty(accessToken)) {
            return setResultError("AccessToken cannot be empty ");
        }
        // 2.从redis中获取accessToken
        String appId = generateToken.getToken(accessToken);
        if (StringUtils.isEmpty(appId)) {
            return setResultError("accessToken  invalid");
        }
        // 3.使用appid查询数据库
        MeiteAppInfo meiteAppInfo = appInfoMapper.findByAppInfo(appId);
        if (meiteAppInfo == null) {
            return setResultError("AccessToken  invalid");
        }
        // 4.返回应用机构信息
        JSONObject data = new JSONObject();
        data.put("appInfo", meiteAppInfo);
        return setResultSuccess(data);
    }
}
