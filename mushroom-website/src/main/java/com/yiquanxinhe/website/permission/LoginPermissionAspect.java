package com.yiquanxinhe.website.permission;

import com.yiquanxinhe.common.constant.CommonConstant;
import com.yiquanxinhe.common.entity.Audience;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;
import com.yiquanxinhe.common.redis.RedisUtil;
import com.yiquanxinhe.common.utils.JwtTokenUtil;
import com.yiquanxinhe.user.entity.type.User;
import com.yiquanxinhe.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:38
 * @Description:
 */

@Aspect
@Component("LoginPermissionAspect")
@Slf4j
public class LoginPermissionAspect {
    @Autowired
    private Audience audience;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(loginPermission)")
    public Object around(ProceedingJoinPoint joinPoint, LoginPermission loginPermission) throws Throwable {
        invoke();
        return joinPoint.proceed();
    }

    public void invoke() {
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new GraphQLCustomException(GraphQLErrorResult.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7);

        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());

        //从token中获取用户名
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());

        try {
            //判断redis中的获取的token是否为空或者用户携带的token是否跟redis中的一致
            if (redisUtil.get(username) == null || !redisUtil.get(username).equals(token)) {
                log.info("### 登录状态过期 ###");
                throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_EXPIRE);
            }
        } catch (RedisConnectionFailureException exception) {
            log.info("### redis出错 ###");
            throw new GraphQLCustomException(GraphQLErrorResult.REDIS_ERROR);
        }

        User user = userService.findUserByUsername(username);

        if (user.getStatus().equals(CommonConstant.USER_STATUS_DISABLE)) {
            throw new GraphQLCustomException(GraphQLErrorResult.USER_ACCOUNT_FORBIDDEN);
        }

        User.set(user);
    }
}
