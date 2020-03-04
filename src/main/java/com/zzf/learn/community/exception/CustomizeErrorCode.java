package com.zzf.learn.community.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NO_FIND(2001,"您找的问题不在了，要不换个试试？"),
    TARGET_PARAM_NO_FIND(2002,"未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试！"),
    SYS_ERROR(2004,"服务繁忙，请重试！！！"),
    TYPE_PARAM_NO_FIND(2005,"评论类型错误或不存在"),
    COMMENT_NO_FIND(2006, "评论不存在");

    private Integer code;
    private String message;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
