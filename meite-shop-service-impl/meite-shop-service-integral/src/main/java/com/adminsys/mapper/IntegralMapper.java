package com.adminsys.mapper;

import com.adminsys.mapper.entity.IntegralEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 积分Mapper
 * @author memo012
 */
@Repository
public interface IntegralMapper {
	/**
	 *  新增积分
	 * @param eiteIntegralEntity 积分实体类
	 * @return
	 */
	@Insert("INSERT INTO `meite_integral` VALUES (NULL, #{userId}, #{paymentId},#{integral}, #{availability}, 0, null, now(), null, now());")
	public int insertIntegral(IntegralEntity eiteIntegralEntity);

	/**
	 *  查询积分
	 * @param paymentId 支付ID
	 * @return
	 */
	@Select("SELECT  id as id ,USER_ID as userId, PAYMENT_ID as PAYMENTID ,INTEGRAL as INTEGRAL ,AVAILABILITY as AVAILABILITY  FROM meite_integral where PAYMENT_ID=#{paymentId}  AND AVAILABILITY='1';")
	public IntegralEntity findIntegral(String paymentId);
}
