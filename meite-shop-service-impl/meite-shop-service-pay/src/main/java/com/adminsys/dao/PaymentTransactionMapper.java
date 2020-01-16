package com.adminsys.dao;

import com.adminsys.dao.entity.PaymentTransactionEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author memo012
 * 支付交易mapper
 */
@Repository
public interface PaymentTransactionMapper {

	/**
	 *  新增支付交易记录
	 * @param paymentTransactionEntity
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO `payment_transaction` VALUES (null, #{payAmount}, '0', #{userId}, #{orderId}, null, null, now(), null, now(),null,#{paymentId});")
	public int insertPaymentTransaction(PaymentTransactionEntity paymentTransactionEntity);

	/**
	 * 交易ID查看交易记录
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM payment_transaction WHERE id=#{id}")
	public PaymentTransactionEntity selectById(Long id);
	
}
