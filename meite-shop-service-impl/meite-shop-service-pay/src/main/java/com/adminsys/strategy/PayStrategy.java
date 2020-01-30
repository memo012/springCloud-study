package com.adminsys.strategy;

import com.adminsys.service.dao.entity.PaymentChannelEntity;
import com.adminsys.pay.output.PayMentTransacDTO;

/**
 * @author memo012
 * 支付策略
 */
public interface PayStrategy {

	/**
	 *  支付功能接口
	 * @param pymentChannel
	 *            渠道参数
	 * @param payMentTransacDTO
	 *            支付参数
	 * @return
	 */
	public String toPayHtml(PaymentChannelEntity pymentChannel, PayMentTransacDTO payMentTransacDTO);

}
