package com.yiquanxinhe.user.mapper;

import com.yiquanxinhe.common.entity.PageQuery;
import com.yiquanxinhe.user.entity.input.InputUser;
import com.yiquanxinhe.user.entity.type.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:41
 * @Description:
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(@Param("username") String username);


    /**
     * 根据角色分页查询用户列表
     * @param roleValue 角色值
     * @param pageQuery 分页查询条件
     * @return 用户列表
     */
    List<User> findUserListByRole(@Param("roleValue") Integer roleValue, @Param("pageQuery") PageQuery pageQuery);

    /**
     * 根据角色分页查询列表数量
     * @param roleValue 角色值
     * @param pageQuery 分页查询条件
     * @return 列表数量
     */
    Integer countForFindUserListByRole(@Param("roleValue") Integer roleValue, @Param("pageQuery") PageQuery pageQuery);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户
     */
    User findUserById(@Param("id") Integer id);

    /**
     * 修改用户信息
     * @param user 修改的用户
     */
    void updateUser(@Param("user") InputUser user);
}
