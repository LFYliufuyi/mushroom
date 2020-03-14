package com.yiquanxinhe.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:35
 * @Description: Base64加密解密工具类
 */
public final class Base64Util {
    private Base64Util(){}
    private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String charset = "utf-8";

    /**
     * 解密
     *
     * @param data 数据
     * @return 返回解密后的数据
     */
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }

            return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，解密异常", data), e);
        }

        return null;
    }

    /**
     * 加密
     *
     * @param data 数据
     * @return 返回加密后的数据
     */
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("字符串：%s，加密异常", data), e);
        }

        return null;
    }
}
