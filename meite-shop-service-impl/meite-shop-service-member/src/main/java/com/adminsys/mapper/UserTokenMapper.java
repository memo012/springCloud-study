package com.adminsys.mapper;

import com.adminsys.mapper.entity.UserTokenDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 *  @author memo012
 *  用户令牌查看
 */
@Repository
public interface UserTokenMapper {

	/**
	 *  通过userId 和 loginType 查询
	 * @param userId
	 * @param loginType
	 * @return
	 */
	@Select("SELECT * FROM meite_user_token WHERE user_id=#{userId} AND login_type=#{loginType} and is_availability ='0'")
	UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("loginType") String loginType);

	/**
	 *  根据 userId + loginType token 的状态修改为不可用
	 * @param token
	 * @return
	 */
	@Update("update meite_user_token set is_availability ='1' where token = #{token}")
	int updateTokenAvailability(@Param("token") String token);

	/**
	 *  token 记录插入一条
	 * @param userTokenDo
	 * @return
	 */
	@Insert("INSERT INTO meite_user_token VALUES (#{id}, #{token},#{loginType}, #{deviceInfor}, 0, #{userId})")
	int insertUserToken(UserTokenDo userTokenDo);
}