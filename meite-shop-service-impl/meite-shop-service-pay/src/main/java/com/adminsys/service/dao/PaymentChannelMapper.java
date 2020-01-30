package com.adminsys.service.dao;


import com.adminsys.service.dao.entity.PaymentChannelEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author memo012
 * 支付渠道mapper
 */
@Repository
public interface PaymentChannelMapper {

	/**
	 *  查询全部渠道信息
	 * @return
	 */
	@Select("SELECT channel_Name  AS channelName , channel_Id AS channelId, merchant_Id AS merchantId,sync_Url AS syncUrl, asyn_Url AS asynUrl,public_Key AS publicKey, private_Key AS privateKey,channel_State AS channelState ,class_ADDRES as classAddres  FROM payment_channel WHERE CHANNEL_STATE='0';")
	public List<PaymentChannelEntity> selectAll();

	/**
	 *  通过渠道ID查询
	 * @param channelId
	 * @return
	 */
	@Select("SELECT channel_Name  AS channelName , channel_Id AS channelId, merchant_Id AS merchantId,sync_Url AS syncUrl, asyn_Url AS asynUrl,public_Key AS publicKey, private_Key AS privateKey,channel_State AS channelState ,class_ADDRES as classAddres  FROM payment_channel WHERE CHANNEL_STATE='0'  AND channel_Id=#{channelId} ;")
	PaymentChannelEntity selectBychannelId(String channelId);
}
