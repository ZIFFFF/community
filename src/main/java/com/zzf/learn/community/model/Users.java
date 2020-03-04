package com.zzf.learn.community.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * Github用户名
     */
    private String name;

    /**
     * GitHub用户id
     */
    private String accountId;

    private String token;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifiedTime;

    private String bio;

    private String avatarUrl;


}
