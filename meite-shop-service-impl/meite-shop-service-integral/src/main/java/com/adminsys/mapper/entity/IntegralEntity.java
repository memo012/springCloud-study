package com.adminsys.mapper.entity;

import java.util.Date;

import lombok.Data;

@Data
public class IntegralEntity {
	/** 主键ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 支付ID */
	private String paymentId;
	/** 积分 */
	private Long integral;
	/** 是否可用 */
	private Integer availability;
	/** 乐观锁 */
	private Integer revision;
	/** 创建人 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新人 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
}
