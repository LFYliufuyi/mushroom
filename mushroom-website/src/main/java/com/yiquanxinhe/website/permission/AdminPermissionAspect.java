package com.yiquanxinhe.website.permission;

import com.yiquanxinhe.common.constant.CommonConstant;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;
import com.yiquanxinhe.user.entity.type.User;
import com.yiquanxinhe.user.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: lfy
 * @Date: 2019/12/25 10:47
 */

@Aspect
@Component
@Slf4j
public class AdminPermissionAspect {

    @Resource
    private LoginPermissionAspect loginPermissionAspect;


    @Around("@annotation(adminPermission)")
    public Object around(ProceedingJoinPoint joinPoint, AdminPermission adminPermission) throws Throwable {
        invoke();
        return joinPoint.proceed();
    }

    public void invoke() {
        loginPermissionAspect.invoke();
        if (!CommonConstant.USER_ROLE_ADMIN.equals(User.get().getRole().getValue())) {
            throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_UNAUTHORISE);
        }
    }
}
