package top.xingao98.community.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xingao98.community.map.QuestionMapper;
import top.xingao98.community.map.UserMapper;
import top.xingao98.community.model.Question;
import top.xingao98.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/9
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String index(HttpServletRequest httpServletRequest){

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "detail", required = false) String detail,
                            @RequestParam(name = "tag", required = false) String tag,
                            HttpServletRequest httpServletRequest,
                            Model model){
        User user = null;
        Cookie cookies[] = httpServletRequest.getCookies();
        if(cookies == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    httpServletRequest.getSession().setAttribute("user", user);
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录！");
            return "publish";
        }
        System.out.println(user);
        model.addAttribute("title",title);
        model.addAttribute("detail",detail);
        model.addAttribute("tag",tag);
        if(title == null || title == ""){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        if(detail == null || detail == ""){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","问题标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setCreaterId(user.getAccountId());
        System.out.println(user.getAccountId());
        question.setTitle(title);
        question.setDetail(detail);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);

        return "redirect:/";
    }
}
