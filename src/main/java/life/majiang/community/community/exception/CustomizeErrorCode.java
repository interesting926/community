package life.majiang.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，要不要换个试试?"),
    TARGET_PARENT_NOT_FOUND(2002,"未选中任何评论活回复评论"),
    SYSTEN_ERROR(2002, "服务器繁忙,请稍后重试"),
    NO_LOGIN(2003,"未登录，不能评论"),
    TYPE_PARAM_WARING(2004,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2005,"你找的评论不存在，要不要换个试试?"),
    CONTENT_IS_EMPTY(2006,"评论不能为空"),
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
