package top.xingao98.community.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xingao98.community.dto.PaginationDTO;
import top.xingao98.community.dto.QuestionDTO;
import top.xingao98.community.exception.CustomizeException;
import top.xingao98.community.exception.CustomizeExceptionCode;
import top.xingao98.community.mapper.QuestionMapper;
import top.xingao98.community.mapper.QuestionMapperExtension;
import top.xingao98.community.mapper.UserMapper;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.QuestionExample;
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
    private QuestionMapperExtension questionMapperExtension;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //判断size是否合理，不合理置1
        if (size < 1) size = 1;
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(page, size, totalCount);
        Integer totalPages = paginationDTO.getTotalPages();
        //判断page是否越界
        if (page > totalPages) page = totalPages;
        if (page < 1) page = 1;
        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(question.getCreatorId());
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer page, Integer size, Integer creatorId) {
        //判断size是否合理，不合理置1
        if (size < 1) size = 1;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorIdEqualTo(creatorId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(page, size, totalCount);
        Integer totalPages = paginationDTO.getTotalPages(); //totalPage 可能为0
        //判断page是否越界
        if (page > totalPages) page = totalPages;           //执行该语句后page可能为0
        if (page < 1) page = 1;
        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorIdEqualTo(creatorId);
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(creatorId);
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
            System.out.println("插入成功");
        } else {
            //添加用户校验 --待验证
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            if(dbQuestion != null && dbQuestion.getId() != question.getId())
                throw new CustomizeException(CustomizeExceptionCode.ACCESS_FORBIDDEN);
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());;
            updateQuestion.setTag(question.getTag());
            updateQuestion.setDetail(question.getDetail());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int status = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if(status == 0){
                throw new CustomizeException(CustomizeExceptionCode.QUESTION_NOT_FOUND);
            }
            else System.out.println("id "+ question.getId()+":更新成功");
        }

        //questionMapper.findById(question.getId());
    }

    public QuestionDTO getById(Long id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeExceptionCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreatorId());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        //阅读数每次加1
        question.setViewCount(1);
        questionMapperExtension.AddViewsById(question);
    }
}
