package com.zzf.learn.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzf.learn.community.model.Notification;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-03-06
 */
public interface NotificationMapper extends BaseMapper<Notification> {

    List<Notification> selectByUserId(Long userId, Integer offset, Integer size);

    Integer count(Long userId);

    Long unreadCount(Long userId);

}
