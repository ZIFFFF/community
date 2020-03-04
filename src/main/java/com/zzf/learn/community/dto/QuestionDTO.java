package com.zzf.learn.community.dto;

import com.zzf.learn.community.model.Users;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private Long createTime;
    private Long modifiedTime;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Users user;
}
