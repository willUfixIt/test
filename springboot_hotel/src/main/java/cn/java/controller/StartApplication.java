/**
 * Project Name:springboot_hotel
 * File Name:StartApplication.java
 * Package Name:cn.java.controller
 * Date:下午2:12:41
 * Copyright (c) 2018, bluemobi All Rights Reserved.
 *
*/

package cn.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Description: springboot入口启动类<br/>
 * Date: 下午2:12:41 <br/>
 * 
 * @author 丁鹏
 * @version
 * @see
 */
@SpringBootApplication(scanBasePackages = { "cn.java.controller", "cn.java.service.impl" })
@EnableAutoConfiguration
@MapperScan(basePackages = { "cn.java.mapper" })
@EnableCaching // 启动Redis缓存
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

}
