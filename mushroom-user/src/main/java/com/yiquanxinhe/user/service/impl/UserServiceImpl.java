package com.yiquanxinhe.user.service.impl;

import com.yiquanxinhe.common.entity.PageInfo;
import com.yiquanxinhe.common.entity.PageQuery;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;
import com.yiquanxinhe.user.entity.input.InputUser;
import com.yiquanxinhe.user.entity.type.User;
import com.yiquanxinhe.user.mapper.UserMapper;
import com.yiquanxinhe.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Raw
 * @Date: 2020/3/12 17:03
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String phoneNum) {
        return userMapper.findUserByUsername(phoneNum);
    }

    @Override
    public List<User> findUserListByRole(Integer roleValue, PageQuery pageQuery) {
        Integer totalCount = userMapper.countForFindUserListByRole(roleValue, pageQuery);
        PageInfo pageInfo = new PageInfo(pageQuery.getPageNum(),pageQuery.getPageSize(),totalCount);
        PageInfo.set(pageInfo);
        return userMapper.findUserListByRole(roleValue, pageQuery);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    @Transactional
    public Boolean updateUser(InputUser user) {
        try {
            if (user.isNull()) {
                throw new GraphQLCustomException(GraphQLErrorResult.SERVICE_PARAM_ERROR);
            }
            userMapper.updateUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
