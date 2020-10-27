package life.majiang.community.community.mode;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Question {
    private  Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private  String tag;

}
