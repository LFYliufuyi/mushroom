package com.yiquanxinhe.user.enums;

import com.yiquanxinhe.common.enum_base.BaseEnum;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:14
 * @Description: 性别
 */
public enum Gender implements BaseEnum<Gender, Integer> {
    MAN(1,"男"),
    WOMAN(0,"女"),
    ;

    static Map<Integer,Gender> enumMap=new HashMap<>();
    static{
        for(Gender e:Gender.values()){
            enumMap.put(e.getValue(), e);
        }
    }

    Gender(Integer value, String desc) {
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

    public static Gender getEnum(Integer value) {
        return enumMap.get(value);
    }
}
