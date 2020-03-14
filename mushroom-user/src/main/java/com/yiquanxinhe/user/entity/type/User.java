package com.yiquanxinhe.user.entity.type;

import com.yiquanxinhe.user.enums.Gender;
import com.yiquanxinhe.user.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:14
 * @Description: 用户
 */
@Setter
@Getter
@ToString
public class User {
    //编号
    private Integer id;
    //用户名（手机号码）
    private String username;
    //密码
    private String password;
    //角色
    private Role role;
    //真实姓名
    private String realName;
    //昵称
    private String nickName;
    //性别
    private Gender gender;
    //头像url
    private String avatarUrl;
    //城市
    private String city;
    //省份
    private String province;
    //国家
    private String country;
    //小程序openId
    private String openId;
    //状态；1：启用；2：禁用
    private Byte status;

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static User get() {
        return threadLocal.get();
    }

    public static void set(User userInfo) {
        threadLocal.set(userInfo);
    }
}
