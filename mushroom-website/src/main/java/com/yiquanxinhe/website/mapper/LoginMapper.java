package com.yiquanxinhe.website.mapper;

import com.yiquanxinhe.user.entity.type.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:40
 * @Description:
 */
@Mapper
public interface LoginMapper {
    /**
     *  查登录用户
     * @param username 用户名
     * @return 用户
     */
    User findLoginUser(String username);
}
