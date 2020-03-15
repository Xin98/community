package top.xingao98.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<QuestionDTO> list(){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.list();
        for(Question question : questions){
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.findById(question.getCreatorId());
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
