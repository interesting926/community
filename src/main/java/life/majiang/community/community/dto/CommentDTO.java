package life.majiang.community.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentID;
    private String connet;
    private Integer type;
}
