package life.majiang.community.community.controller;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.mapper.CommentMapper;
import life.majiang.community.community.mode.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentID());
        comment.setContent(commentDTO.getConnet());
        comment.setType(commentDTO.getType());
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreat());
        comment.setCommentor(1);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }
}
