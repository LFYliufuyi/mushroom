package com.yiquanxinhe.common.utils;

import com.yiquanxinhe.common.entity.Audience;
import com.yiquanxinhe.common.error_result.GraphQLErrorResult;
import com.yiquanxinhe.common.exception.GraphQLCustomException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:36
 * @Description: token工具类
 */
public final class JwtTokenUtil {
    private JwtTokenUtil(){}
    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String AUTH_HEADER_KEY = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 解析jwt
     *
     * @param jsonWebToken   需要解析的token
     * @param base64Security 加密方式
     * @return 解析后
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (ExpiredJwtException eje) {
            log.error("===== Token过期 =====", eje);
            throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("===== token解析异常 =====", e);
            throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_TOKEN_INVALID);
        }
    }

    /**
     * 构建jwt
     *
     * @param userId   用户id
     * @param username 用户名
     * @param roleValue   角色值
     * @param audience JWT的签发主体
     * @return 生成的JWT
     */
    public static String createJWT(String userId, String username, Integer roleValue, Audience audience) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            //userId是重要信息，进行加密下
            String encryId = Base64Util.encode(userId);

            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("userId", userId)
                    .claim("roleValue", roleValue)
                    // 代表这个JWT的主体，即它的所有人
                    .setSubject(username)
                    // 代表这个JWT的签发主体；
                    .setIssuer(audience.getClientId())
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(audience.getName())
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }

            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new GraphQLCustomException(GraphQLErrorResult.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token          token
     * @param base64Security 加密方式
     * @return 用户名
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token          token
     * @param base64Security 加密方式
     * @return 用户id
     */
    public static Integer getUserId(String token, String base64Security) {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Integer.parseInt(userId);
    }

    /**
     * 从token中获取用户角色的值
     *
     * @param token          token
     * @param base64Security 加密方式
     * @return 用户角色的值
     */
    public static Integer getRoleValue(String token, String base64Security) {
        return parseJWT(token, base64Security).get("roleValue", Integer.class);
    }

    /**
     * 是否已过期
     *
     * @param token          token
     * @param base64Security 加密方式
     * @return 是或否
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}
