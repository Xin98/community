package top.xingao98.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xinGao 2020/3/9
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String Hello(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "Hello";
    }
}
