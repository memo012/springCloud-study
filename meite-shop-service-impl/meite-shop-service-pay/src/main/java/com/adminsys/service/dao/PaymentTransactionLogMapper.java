package com.adminsys.service.dao;

import com.adminsys.service.dao.entity.PaymentTransactionLogEntity;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author memo012
 * 支付交易日志
 */
@Repository
public interface PaymentTransactionLogMapper {

	/**
	 *  新增日志
	 * @param paymentTransactionLog
	 * @return
	 */
	@Insert("INSERT INTO `payment_transaction_log` VALUES (NULL, NULL, #{asyncLog},NULL, #{transactionId}, null, null, NOW(), null, NOW());")
	public int insertTransactionLog(PaymentTransactionLogEntity paymentTransactionLog);

}
