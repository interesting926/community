package life.majiang.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换个试试?"),
    TARGET_PARENT_NOT_FOUND(2002,"未选中任何评论活回复评论"),
    SYSTEN_ERROR(2003, "服务器繁忙,请稍后重试"),
    NO_LOGIN(2004,"未登录，不能评论"),
    TYPE_PARAM_WARING(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"你找的评论不存在，要不要换个试试?"),
    CONTENT_IS_EMPTY(2007,"评论不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人的信息吗"),
    NOTIFICATION_NOT_FOUND(2009,"消息不翼而飞了？"),
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code ,String message) {
        this.code = code;
       this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
