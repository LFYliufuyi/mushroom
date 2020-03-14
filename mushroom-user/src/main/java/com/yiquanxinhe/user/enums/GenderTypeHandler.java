package com.yiquanxinhe.user.enums;

import com.yiquanxinhe.common.enum_base.BaseEnum;
import com.yiquanxinhe.common.enum_base.BaseEnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;


/**
 * @Author: Raw
 * @Date: 2020/3/12 16:14
 * @Description: 性别枚举处理
 */
@MappedTypes({Gender.class})
public class GenderTypeHandler<E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    public GenderTypeHandler(Class<E> type) {
        super(type);
    }
}
