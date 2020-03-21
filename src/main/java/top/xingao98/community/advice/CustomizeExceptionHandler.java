package top.xingao98.community.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.xingao98.community.exception.CustomizeException;
import top.xingao98.community.exception.CustomizeExceptionCode;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xinGao 2020/3/19
 */
@ControllerAdvice //它是Controller的全局形式
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    //@ResponseBody
    //ResponseBody 等价于 ModelAndView
    ModelAndView handle(HttpServletRequest request,
                                           Throwable e,
                                           Model model) {
        if(e instanceof CustomizeException){
            model.addAttribute("message", "ExceptionHandler："+ e.getMessage());
        }
        else{
            model.addAttribute("message", "ExceptionHandler："+ CustomizeExceptionCode.SERVER_UNKNOW_ERROR.getMessage());
        }
        return new ModelAndView("error");
    }
}
