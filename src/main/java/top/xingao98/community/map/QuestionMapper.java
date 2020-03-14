package top.xingao98.community.map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.xingao98.community.model.Question;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("insert into publish (title, creater_id, detail, tag, gmt_create, gmt_modified) values (#{title}, #{createrId}, #{detail}, #{tag}, #{gmtCreate}, #{gmtModified})")
    void create(Question question);
}
