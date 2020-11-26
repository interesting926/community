package life.majiang.community.community.Enum;

public enum CommentTypeEnum {
    QUESTIONG(1),
    COMMENT(2);
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    CommentTypeEnum(int type) {
        this.type = type;
    }

    public static boolean isExst(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return type;
    }
}
