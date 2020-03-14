package com.yiquanxinhe.website.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.yiquanxinhe.common.constant.CommonConstant;
import com.yiquanxinhe.common.entity.PageInfo;
import com.yiquanxinhe.common.entity.PageQuery;
import com.yiquanxinhe.user.entity.type.User;
import com.yiquanxinhe.user.service.UserService;
import com.yiquanxinhe.website.permission.LoginPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:14
 * @Description:
 */
@Component
public class Query implements GraphQLQueryResolver {
    /**
     * 用户
     */
    @Autowired
    private UserService userService;

    @LoginPermission
    public User currentUser() {
        return User.get();
    }
    
    public User user(Integer id) {
        return userService.findUserById(id);
    }

    public List<User> workers(PageQuery pageQuery) {
        return userService.findUserListByRole(CommonConstant.USER_ROLE_WORKER, pageQuery);
    }

    public PageInfo pageInfo() {
        return PageInfo.get();
    }
}
