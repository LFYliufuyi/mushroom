package com.yiquanxinhe.user.enums;

import com.yiquanxinhe.common.enum_base.BaseEnum;
import com.yiquanxinhe.common.enum_base.BaseEnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;

/**
 * @Author: Raw
 * @Date: 2020/3/12 16:32
 * @Description: 角色枚举处理
 */
@MappedTypes({Role.class})
public class RoleTypeHandler <E extends Enum<E> & BaseEnum> extends BaseEnumTypeHandler<E> {
    public RoleTypeHandler(Class<E> type) {
        super(type);
    }
}
