package com.zzf.learn.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String CategoryName;
    private List<String> Tags;
}
