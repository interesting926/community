package life.majiang.community.community.mapper;

import life.majiang.community.community.mode.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmtCreate,gmtModified,AVATARURL) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarurl}")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where ACCOUNT_ID = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name},token=#{token},gmtCreate=#{gmtCreate},gmtModified=#{gmtModified},AVATAR_URL=#{avatarurl} where account_id = #{accountId}")
    void update(User user);
}
