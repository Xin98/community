package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xingao98.community.dto.PaginationDTO;
import top.xingao98.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/9
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        Model model) {
        PaginationDTO paginationDTO = questionService.list(page, size);
        System.out.println("paginationDTO" + paginationDTO);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
