package com.zzf.learn.community.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 郑梓锋
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Notification {

    private Long id;

    private Long notifier;

    private Long receiver;

    private Long outerid;

    private Integer type;

    private Long createTime;

    private Integer status;

    private String notifierName;

    private String outerTitle;

}
