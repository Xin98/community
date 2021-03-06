package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.xingao98.community.dto.QuestionDTO;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.User;
import top.xingao98.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/18
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           HttpServletRequest request,
                           Model model) {

        QuestionDTO questionDTO = questionService.getById(id);
        //增加阅读量
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        User user = (User) request.getSession().getAttribute("user");
        //用来判断问题页面是否可被编辑
        if(user != null) model.addAttribute("id", user.getId());
        return "question";
    }
}
