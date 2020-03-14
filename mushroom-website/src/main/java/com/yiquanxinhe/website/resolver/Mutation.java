package com.yiquanxinhe.website.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.yiquanxinhe.user.entity.input.InputUser;
import com.yiquanxinhe.user.service.UserService;
import com.yiquanxinhe.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:14
 * @Description:
 */
@Component
public class Mutation implements GraphQLMutationResolver {
    /**
     * 登录
     */
    @Autowired
    private LoginService loginService;

    public String login(String username, String password) {
        return loginService.login(username, password);
    }

    /**
     * 用户
     */
    @Autowired
    private UserService userService;
    
    public Boolean updateUser(InputUser user){
        return userService.updateUser(user);
    }
    
}
