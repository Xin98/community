package top.xingao98.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.QuestionExample;

import java.util.List;

public interface QuestionMapperExtension {
    //利用数据库的性质，创建并发安全的更新阅读数的方法
    //view_count = view_count + record.viewCount
    int AddViewsById(Question record);
}