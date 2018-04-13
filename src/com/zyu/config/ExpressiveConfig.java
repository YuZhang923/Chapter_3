package com.zyu.config;

import com.zyu.pojo.BlankDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-13 19:04
 * Description: 运行时值的注入
 */
@Configuration
@PropertySource("aa.properties") //申明属性源
@ComponentScan("com.zyu.pojo")
@ImportResource("spring.xml")
public class ExpressiveConfig {
    @Autowired
    private Environment environment;

    @Bean
    public BlankDisc disc(){
        return new BlankDisc(environment.getProperty("disc.title"),environment.getProperty("disc.artist"));//从环境检索属性值
    }

    //配置支持属性资源占位符bean，基于Spring Environment及其属性源来解析占位符
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


}
