package com.yiquanxinhe.common.enum_base;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:10
 * @Description: 枚举接口
 */
public interface BaseEnum<E extends Enum<?>, T> {
    T getValue();
    String getDesc();
}
