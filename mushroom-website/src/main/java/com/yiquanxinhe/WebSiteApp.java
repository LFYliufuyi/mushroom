package com.yiquanxinhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author: Raw
 * @Date: 2020/3/13 19:01
 * @Description: 程序启动
 */
@SpringBootApplication
@Import(CommonConfig.class)
public class WebSiteApp {
    public static void main(String[] args) {
        SpringApplication.run(WebSiteApp.class, args);
    }
}
