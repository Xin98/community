package top.xingao98.community.provider;

import okhttp3.*;
import org.springframework.stereotype.Component;
import top.xingao98.community.dto.AccessTokenDTO;

import java.io.IOException;
import com.alibaba.fastjson.JSON;
import top.xingao98.community.dto.GithubUser;

/**
 * Created by xinGao 2020/3/10
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String token = response.body().string().split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUserInfo(String token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + token)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String useInfo = response.body().string();
            GithubUser githubUser = JSON.parseObject(useInfo, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
