package com.yiquanxinhe.website.service.impl;

import com.yiquanxinhe.common.constant.CommonConstant;
import com.yiquanxinhe.common.entity.Audience;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;
import com.yiquanxinhe.common.redis.RedisUtil;
import com.yiquanxinhe.common.utils.EncryptUtil;
import com.yiquanxinhe.common.utils.JwtTokenUtil;
import com.yiquanxinhe.user.entity.type.User;
import com.yiquanxinhe.website.mapper.LoginMapper;
import com.yiquanxinhe.website.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:38
 * @Description:
 */

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private Audience audience;
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public String login(String username, String password) {
        User user = loginMapper.findLoginUser(username);
        System.out.println(EncryptUtil.encryptPassword(username, password, user.getRole().getValue()));
        //判断是否有此用户与密码是否正确
        if (user == null || !EncryptUtil.encryptPassword(username, password, user.getRole().getValue()).equals(user.getPassword())) {
            throw new GraphQLCustomException(GraphQLErrorResult.USER_LOGIN_ERROR);
        }
        if (user.getStatus().equals(CommonConstant.USER_STATUS_DISABLE)) {
            throw new GraphQLCustomException(GraphQLErrorResult.USER_ACCOUNT_FORBIDDEN);
        }
        String userId = user.getId().toString();
        Integer roleValue = user.getRole().getValue();

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, roleValue, audience);

        //把token存进redis中.时间单位为秒
        redisUtil.set(username, token, 7200);

        return token;
    }
}
