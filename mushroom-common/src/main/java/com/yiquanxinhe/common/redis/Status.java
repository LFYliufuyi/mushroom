package com.yiquanxinhe.common.redis;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:26
 * @Description: redis状态枚举
 */
public class Status {
    /**
     * 过期时间相关枚举
     */
    public enum ExpireEnum {
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS)

        ;

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
