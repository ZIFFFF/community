package com.zzf.learn.community.controller;


import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.zzf.learn.community.dto.CommentDTO;
import com.zzf.learn.community.dto.ResultDTO;
import com.zzf.learn.community.enums.CommentTypeEnum;
import com.zzf.learn.community.exception.CustomizeErrorCode;
import com.zzf.learn.community.model.Comment;
import com.zzf.learn.community.model.Users;
import com.zzf.learn.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request){
        Users user = (Users)request.getSession().getAttribute("user");
        if (user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setModifiedTime(comment.getCreateTime());
        comment.setCommentator(24L);
        commentService.insert(comment);
        Map<Object, Object> objectObjectMap = new HashMap<>();
        objectObjectMap.put("message", "成功");
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

}
