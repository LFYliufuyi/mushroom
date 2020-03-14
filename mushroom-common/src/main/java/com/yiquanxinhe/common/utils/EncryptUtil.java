package com.yiquanxinhe.common.utils;

import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;

import java.security.MessageDigest;

/**
 * @Author: Raw
 * @Date: 2020/3/14 0:24
 * @Description: 密码加密工具类
 */
public class EncryptUtil {
    private EncryptUtil() {
    }


    public static String encryptPassword(String username, String password, Integer roleValue) {
        byte[] salt = (roleValue.toString() + username).getBytes();
        byte[] passwords = password.getBytes();
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwords);
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (Exception e) {
            throw new GraphQLCustomException(GraphQLErrorResult.SYSTEM_INNER_ERROR);
        }
        return generatedPassword;
    }
}
