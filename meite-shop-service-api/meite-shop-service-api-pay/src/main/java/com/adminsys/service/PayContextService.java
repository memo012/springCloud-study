package com.adminsys.service;

import com.adminsys.base.BaseResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 支付渠道接口
 * @author memo012
 */
public interface PayContextService {

	/**
	 *
	 * @param channelId
	 * @param payToken
	 * @return
	 */
	@GetMapping("/toPayHtml")
	public BaseResponse<JSONObject> toPayHtml(@RequestParam("channelId")String channelId, @RequestParam("payToken")String payToken);
}