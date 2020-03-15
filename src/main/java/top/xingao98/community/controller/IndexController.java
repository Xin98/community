package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.xingao98.community.dto.QuestionDTO;
import top.xingao98.community.map.UserMapper;
import top.xingao98.community.model.User;
import top.xingao98.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xinGao 2020/3/9
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest,
                        Model model){
        List<QuestionDTO> questions = questionService.list();
        model.addAttribute("questions",questions);
        Cookie cookies[] = httpServletRequest.getCookies();
        if(cookies == null)
            return "index";
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    httpServletRequest.getSession().setAttribute("user", user);
                }
            }
        }
        return "index";
    }
}
