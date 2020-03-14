package com.yiquanxinhe.user.entity.input;

import com.yiquanxinhe.user.enums.Gender;
import com.yiquanxinhe.user.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:30
 * @Description: input用户
 */
@Setter
@Getter
@ToString
public class InputUser {
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
    //性别
    private Gender gender;

    public boolean isNull() {
        if (this.username == null && this.password == null && this.role == null && this.realName == null && this.gender == null) {
            return true;
        }
        return false;
    }

}
