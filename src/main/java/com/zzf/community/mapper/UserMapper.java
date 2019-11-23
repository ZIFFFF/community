package com.zzf.community.mapper;

import com.zzf.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into users (name,account_id,token,create_time,modified_time) values (#{name},#{accountId},#{token},#{createTime},#{modifiedTime})")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);
}
