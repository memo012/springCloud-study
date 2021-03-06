package com.adminsys.mapper;

import com.adminsys.mapper.entity.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author qiang
 *  用户mapper
 */
@Repository
public interface UserMapper {

	@Insert("INSERT INTO `meite_user` VALUES (#{userId},#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null)")
	int register(UserDo userEntity);

	@Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile}")
	UserDo existMobile(@Param("mobile") String mobile);

	@Select("SELECT * from `meite-member`.meite_user WHERE MOBILE=#{mobile} and password=#{password}")
	UserDo login(@Param("mobile") String mobile, @Param("password") String password);

	@Select("SELECT * FROM meite_user WHERE user_Id=#{userId}")
	UserDo findByUserId(@Param("userId") Long userId);
}