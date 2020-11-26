package life.majiang.community.community.mapper;

import life.majiang.community.community.mode.Comment;
import life.majiang.community.community.mode.CommentExample;
import life.majiang.community.community.mode.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentEXTMapper {
    int incCommentCount( Comment comment);
}