package top.xingao98.community.map;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.xingao98.community.model.Question;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, creator_id, detail, tag, gmt_create, gmt_modified) values (#{title}, #{creatorId}, #{detail}, #{tag}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);

    @Select("select * from question order by gmt_create desc limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select * from question where creator_id = #{id} order by gmt_create desc limit #{offset},#{size}")
    List<Question> listByCreatorId(@Param("offset") Integer offset, @Param("size") Integer size, @Param("id") Integer id);

    @Select("select count(1) from question")
    Integer getRecordsCount();

    @Select("select count(1) from question where creator_id = #{id}")
    Integer getRecordsCountByCreatorId(@Param("id") Integer id);

    @Select("select * from question where id = #{id}")
    Question getById(Integer id);

    @Update("update question set title = #{title}, detail = #{detail}, tag = #{tag}, gmt_modified = #{gmtModified} where id = #{id}")
    void update(Question question);
}
