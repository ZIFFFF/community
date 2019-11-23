package com.zzf.community.mapper;

import com.zzf.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,create_time,modified_time) values (#{name},#{account_id},#{token},#{create_time},#{modified_time})")
    void insert(User user);
}
