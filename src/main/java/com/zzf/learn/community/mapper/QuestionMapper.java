package com.zzf.learn.community.mapper;

import com.zzf.learn.community.model.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<Question> questionList(@Param("offset") Integer offset, @Param("size") Integer size);

    Integer counts();

    Integer count(@Param("userId") Long user_id);

    List<Question> selectByUserId(@Param("userId") Long userId, @Param("offset") Integer offset, @Param("size") Integer size);

    Question selectById(@Param("id") Long id);

    int updateQuestion(Question question);

    void insertQuestion(Question question);

    int incView(Question question);

    int incComment(Question question);

}
