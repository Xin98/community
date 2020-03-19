package top.xingao98.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xingao98.community.mapper.UserMapper;
import top.xingao98.community.model.User;
import top.xingao98.community.model.UserExample;

import java.util.List;

/**
 * Created by xinGao 2020/3/18
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        //User dbUser = userMapper.getByAccountId(user.getAccountId());
        if(users == null || users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        else{
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
            //userMapper.update(dbUser);
        }
    }
}
