package life.majiang.community.community.dto;

import life.majiang.community.community.mode.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private  long id;
    private String title;
    private String description;
    private  String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
