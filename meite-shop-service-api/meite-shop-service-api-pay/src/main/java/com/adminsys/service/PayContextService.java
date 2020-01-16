package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支付渠道接口
 * @author memo012
 */
public interface PayContextService {
	@GetMapping("/toPayHtml")
	public BaseResponse<JSONObject> toPayHtml(String channelId, String payToken);
}