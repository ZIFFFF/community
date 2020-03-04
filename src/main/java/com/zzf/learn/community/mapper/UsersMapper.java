package com.zzf.learn.community.mapper;

import com.zzf.learn.community.model.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.catalina.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
public interface UsersMapper extends BaseMapper<Users> {

    Users findByToken(String token);

    Users findByAccountId(String accountId);

    void updateUser(Users dbuser);

    Users findById(Long creator);

    List<Users> selectByIds(List<Long> userIds);
}
