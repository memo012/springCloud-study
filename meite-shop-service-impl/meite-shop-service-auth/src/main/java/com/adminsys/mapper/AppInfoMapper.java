package com.adminsys.mapper;

import com.adminsys.mapper.entity.MeiteAppInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AppInfoMapper {

	@Insert("INSERT INTO `meite_app_info` VALUES (null,#{appName}, #{appId}, #{appSecret}, '0', null, null, null, null, null);")
	public int insertAppInfo(MeiteAppInfo meiteAppInfo);

	@Select("SELECT *  FROM meite_app_info where app_id=#{appId} and app_secret=#{appSecret}; ")
	public MeiteAppInfo selectByAppInfo(@Param("appId") String appId, @Param("appSecret") String appSecret);

	@Select("SELECT *  FROM meite_app_info where app_id=#{appId}  ")
	public MeiteAppInfo findByAppInfo(@Param("appId") String appId);
}
