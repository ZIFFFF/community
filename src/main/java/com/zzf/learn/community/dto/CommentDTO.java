package com.zzf.learn.community.dto;

import com.zzf.learn.community.model.Users;
import lombok.Data;

@Data
public class CommentDTO {

    private Long parentId;
    private String content;
    private Integer type;
    private Long commentator;
    private Long createTime;
    private Long modifiedTime;
    private Long likeCount;
    private Integer commentCount;
    private Users user;
}
