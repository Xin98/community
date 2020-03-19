package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.xingao98.community.dto.PaginationDTO;
import top.xingao98.community.model.User;
import top.xingao98.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/17
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model) {
        User user = (User)request.getSession().getAttribute("user");
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName", "我的问题");
            PaginationDTO paginationDTO = questionService.list(page, size, user.getId());
            model.addAttribute("paginationDTO", paginationDTO);
        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName", "我的回复");
        }
        if("likes".equals(action)){
            model.addAttribute("section","likes");
            model.addAttribute("sectionName", "我的获赞");
        }
        if("stars".equals(action)){
            model.addAttribute("section","stars");
            model.addAttribute("sectionName", "我的关注");
        }
        return "profile";
    }
}
