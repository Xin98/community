package top.xingao98.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.xingao98.community.mapper.UserMapper;
import top.xingao98.community.model.User;
import top.xingao98.community.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by xinGao 2020/3/16
 */
@Component
public class SessionIntercepor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookies[] = request.getCookies();
        if (cookies == null)
            return true;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();

                UserExample example = new UserExample();
                example.createCriteria()
                        .andTokenEqualTo(token);
                List<User> users = userMapper.selectByExample(example);
                if (users != null && users.size() != 0) {
                    request.getSession().setAttribute("user", users.get(0));
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
