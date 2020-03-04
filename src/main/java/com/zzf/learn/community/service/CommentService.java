package com.zzf.learn.community.service;

import com.zzf.learn.community.dto.CommentDTO;
import com.zzf.learn.community.enums.CommentTypeEnum;
import com.zzf.learn.community.exception.CustomizeErrorCode;
import com.zzf.learn.community.exception.CustomizeException;
import com.zzf.learn.community.mapper.CommentMapper;
import com.zzf.learn.community.mapper.QuestionMapper;
import com.zzf.learn.community.mapper.UsersMapper;
import com.zzf.learn.community.model.Comment;
import com.zzf.learn.community.model.Question;
import com.zzf.learn.community.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Transactional  //事务注解 以下均执行事务
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NO_FIND);
        }

        if (comment.getType() == null || CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_NO_FIND);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_FIND);
            }
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectById(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NO_FIND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionMapper.incComment(question);
        }
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        List<Comment> comments = commentMapper.selectByIdAndType(id, type.getType());
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为Map
        List<Users> users = usersMapper.selectByIds(userIds);
        Map<Long, Users> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
           CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
