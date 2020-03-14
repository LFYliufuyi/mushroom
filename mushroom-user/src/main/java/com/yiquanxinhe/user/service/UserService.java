package com.yiquanxinhe.user.service;

import com.yiquanxinhe.common.entity.PageQuery;
import com.yiquanxinhe.user.entity.input.InputUser;
import com.yiquanxinhe.user.entity.type.User;

import java.util.List;

/**
 * @Author: Raw
 * @Date: 2020/3/12 17:02
 * @Description: 用户接口
 */
public interface UserService {
    /**
     * 根据用户名查用户
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);

    /**
     * 根据用户角色分页查询用户列表
     * @param roleValue 角色值
     * @param pageQuery 分页条件
     * @return 用户列表
     */
    List<User> findUserListByRole(Integer roleValue, PageQuery pageQuery);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户
     */
    User findUserById(Integer id);

    /**
     * #修改用户信息
     * @param user 修改的用户
     * @return 是否成功
     */
    Boolean updateUser(InputUser user);
}
