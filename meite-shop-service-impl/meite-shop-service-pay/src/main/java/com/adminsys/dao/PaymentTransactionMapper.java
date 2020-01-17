package com.adminsys.dao;

import com.adminsys.dao.entity.PaymentTransactionEntity;
import org.apache.ibatis.annotations.*;
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

	/**
	 * 支付ID查看交易记录
	 * @param paymentId
	 * @return
	 */
	@Select("SELECT * FROM payment_transaction WHERE PAYMENT_ID=#{paymentId}")
	public PaymentTransactionEntity selectByPaymentId(String paymentId);

	/**
	 *  更新交易支付状态
	 * @param paymentStatus
	 * @param paymentId
	 * @return
	 */
	@Update("update payment_transaction SET PAYMENT_STATUS=#{paymentStatus} WHERE PAYMENT_ID=#{paymentId}; ")
	public int updatePaymentStatus(@Param("paymentStatus") Integer paymentStatus, @Param("paymentId") String paymentId);
	
}
