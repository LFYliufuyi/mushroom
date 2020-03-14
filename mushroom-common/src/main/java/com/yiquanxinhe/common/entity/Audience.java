package com.yiquanxinhe.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Raw
 * @Date: 2020/3/12 2:08
 * @Description: JWT的对象
 */
@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

}
