package com.zzf.learn.community.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzf.learn.community.model.Comment;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
public interface CommentMapper extends BaseMapper<Comment> {


    List<Comment> selectByIdAndType(Long id, Integer type);

}
