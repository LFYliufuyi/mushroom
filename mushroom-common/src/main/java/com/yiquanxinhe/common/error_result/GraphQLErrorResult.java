package com.yiquanxinhe.common.error_result;

import com.yiquanxinhe.common.enum_base.BaseEnum;
import lombok.Setter;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:13
 * @Description: 错误枚举
 */
public enum GraphQLErrorResult implements BaseEnum {

    /* graphql错误：10001-19999 */
    GRAPHQL_NULL_ERROR(10001, "不允许为null错误"),
    GRAPHQL_PARSE_ERROR(10002, "序列化Scalar时错误"),
    GRAPHQL_DEFINES_FIELDS_ERROR(10003, "未在Schema中定义的field错误"),
    GRAPHQL_VALIDATION_ERROR(10004, "请求的field错误"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录，请先登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_HAS_EXISTED(20004, "用户已存在"),
    USER_FORMAT_ERROR(20005, "手机号码格式错误"),

    /* 权限错误：30001-39999 */
    PERMISSION_UNAUTHENTICATED(30001, "此操作需要登陆系统！"),
    PERMISSION_UNAUTHORISE(30002, "权限不足，无权操作！"),
    PERMISSION_EXPIRE(30003, "登录状态过期！"),
    PERMISSION_TOKEN_EXPIRED(30004, "token已过期"),
    PERMISSION_LIMIT(30005, "访问次数受限制"),
    PERMISSION_TOKEN_INVALID(30006, "无效token"),
    PERMISSION_SIGNATURE_ERROR(30007, "签名失败"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    REDIS_ERROR(40002, "redis出错"),

    /* 业务错误：50001-59999 */
    SERVICE_PARAM_ERROR(50001,"参数出错")
            ;
    GraphQLErrorResult(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Setter
    Integer value;
    @Setter
    String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
