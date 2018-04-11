package com.zyu.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-11 20:20
 * Description:
 */
@Configuration
//@Profile("dev") //如果应用在类级别上，当这个dev profile没有激活时，该类中的所有@Bean都会被忽略。
public class SystemConfig {

    //可以配置多个数据源
    @Bean
    @Profile("dev") //当指定的profile被激活时，才会创建bean，而没有被声明@Profile的其他bean始终会被创建。
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setUser("root");
        dataSource.setPassword("admin");
        dataSource.setMinPoolSize(3);
        dataSource.setMaxPoolSize(20);
        return dataSource;
    }
}
