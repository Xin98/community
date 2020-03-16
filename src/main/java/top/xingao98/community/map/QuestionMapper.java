package top.xingao98.community.map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select count(1) from question")
    Integer getRecordsCount();
}
