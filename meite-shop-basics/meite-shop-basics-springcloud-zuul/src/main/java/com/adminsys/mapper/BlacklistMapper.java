package com.adminsys.mapper;

import com.adminsys.mapper.entity.BlackListDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author memo012
 *  @description 拦截黑名单dao层
 */
@Repository
public interface BlacklistMapper {

	/**
	 *  查询黑名单
	 * @param ipAddres
	 * @return
	 */
	@Select("select * from meite_gateway where  ip_addres =#{ipAddres} and  restriction_type='1' ")
	BlackListDao findBlacklist(String ipAddres);

}
