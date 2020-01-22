package com.adminsys.mapper;

import com.adminsys.mapper.entity.MeiteAppToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Qiang
 * @version 1.0
 * @description TODO
 * @date 2020/1/22 下午5:20
 **/
@Repository
public interface APPTokenMapper {

    /**
     *  新增token使用情况
     * @param meiteAppToken
     * @return
     */
    @Insert("INSERT INTO `meite_app_token` VALUES (null,#{appName}, #{appId}, #{appToken}, 0, null);")
    public int insertAppToken(MeiteAppToken meiteAppToken);


    /**
     *  查询是否存在该token
     * @param appId
     * @return
     */
    @Select("select * from meite_app_token where app_id = #{appId} and is_verify = 0")
    MeiteAppToken selectAccessTokenByAppId(String appId);

    /**
     *  修改token为不可用状态
     * @param appId
     * @return
     */
    @Update("update meite_app_token set is_verify = 1 where app_id = #{appId}")
    int updTokenStatus(String appId);

}
