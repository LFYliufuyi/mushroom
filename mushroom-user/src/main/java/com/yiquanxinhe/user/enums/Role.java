package com.yiquanxinhe.user.enums;

import com.yiquanxinhe.common.enum_base.BaseEnum;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:31
 * @Description: 角色
 */
public enum Role implements BaseEnum<Role, Integer> {
    ADMIN(1,"管理员"),
    WORKER(2,"工作人员")
    ;

    static Map<Integer,Role> enumMap=new HashMap<>();
    static{
        for(Role e:Role.values()){
            enumMap.put(e.getValue(), e);
        }
    }

    Role(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Setter
    private Integer value;
    @Setter
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static Role getEnum(Integer value) {
        return enumMap.get(value);
    }

}
