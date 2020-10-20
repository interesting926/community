package life.majiang.community.community.mapper;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mode.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void creat(Question question);

    @Select("select * from QUESTION")
    List<Question> findList();
}
