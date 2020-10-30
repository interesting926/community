package life.majiang.community.community.mapper;

import life.majiang.community.community.mode.Question;
import life.majiang.community.community.mode.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}