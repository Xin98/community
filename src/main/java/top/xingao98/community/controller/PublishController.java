package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xingao98.community.dto.QuestionDTO;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.User;
import top.xingao98.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/9
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String index() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "detail", required = false) String detail,
                            @RequestParam(name = "tag", required = false) String tag,
                            @RequestParam(name = "id", required = false) Integer id,
                            HttpServletRequest httpServletRequest,
                            Model model) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        model.addAttribute("title", title);
        model.addAttribute("detail", detail);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "问题标题不能为空");
            return "publish";
        }
        if (detail == null || detail == "") {
            model.addAttribute("error", "问题描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "问题标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setCreatorId(user.getId());
        question.setTitle(title);
        question.setDetail(detail);
        question.setTag(tag);
        question.setId(id);
        System.out.println(question);
        questionService.createOrUpdate(question);
        //questionMapper.create(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        //为了防止任意一个人都可以修改问题
        //这里查询问题时需要添加用户校验
        QuestionDTO questionDTO = questionService.getById(id);
        if(questionDTO != null){
            model.addAttribute("title", questionDTO.getTitle());
            model.addAttribute("detail", questionDTO.getDetail());
            model.addAttribute("tag", questionDTO.getTag());
            model.addAttribute("id",questionDTO.getId());
            return "publish";
        }
        else{
            //model.addAttribute("error", "问题不存在");
            return "redirect:/error";
        }

    }
}
