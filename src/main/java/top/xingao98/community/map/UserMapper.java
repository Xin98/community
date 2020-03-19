package top.xingao98.community.map;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.xingao98.community.model.User;

import java.util.List;

/**
 * Created by xinGao 2020/3/12
 */
@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id, name, token, gmt_create, gmt_modified, avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User getByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Integer id);

    @Select("select * from user where account_id = #{accountId}")
    User getByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where account_id = #{accountId}")
    void update(User dbUser);
}
