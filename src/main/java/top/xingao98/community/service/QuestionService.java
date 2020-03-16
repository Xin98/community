package top.xingao98.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xingao98.community.dto.PaginationDTO;
import top.xingao98.community.dto.QuestionDTO;
import top.xingao98.community.map.QuestionMapper;
import top.xingao98.community.map.UserMapper;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinGao 2020/3/14
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size){
        //判断size是否合理，不合理置1
        if(size < 1) size = 1;
        Integer totalCount = questionMapper.getRecordsCount();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(page, size, totalCount);
        Integer totalPages = paginationDTO.getTotalPages();
        //判断page是否越界
        if(page < 1) page = 1;
        if(page > totalPages) page = totalPages;
        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.list(offset, size);
        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreatorId());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
