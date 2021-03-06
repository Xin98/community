package top.xingao98.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xingao98.community.dto.AccessTokenDTO;
import top.xingao98.community.dto.GithubUser;
import top.xingao98.community.model.User;
import top.xingao98.community.provider.GithubProvider;
import top.xingao98.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by xinGao 2020/3/10
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client_id}")
    private String clientId;

    @Value("${github.client_secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse httpServletResponse){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser githubUser = githubProvider.getUserInfo(accessToken);

        if(githubUser != null){
            //登录成功
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(githubUser.getId());
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatarUrl());

            userService.insertOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(36*60*60);
            httpServletResponse.addCookie(cookie);
            //httpServletRequest.getSession().setAttribute("githubUser", githubUser);
            System.out.println("登录成功，正在跳转...");
            return "redirect:/";
        }else{
            //登录失败
            System.out.println("登录失败，正在跳转...");
            return "redirect:/";
        }
    }
}
