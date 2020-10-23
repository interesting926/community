package life.majiang.community.community.mapper;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mode.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void creat(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> findList(@Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from QUESTION")
    Integer Count();

    @Select("select * from QUESTION where creator = #{userid} limit #{offset},#{size}")
    List<Question> findUserList(@Param("userid")Integer userid,@Param("offset")Integer offset, @Param("size")Integer size);

    @Select("select count(1) from QUESTION where creator = #{userid}")
    Integer CountUser(@Param("userid")Integer userid);

    @Select("select * from question where id = #{id}")
    Question findById(@Param("id") Integer id);
}
