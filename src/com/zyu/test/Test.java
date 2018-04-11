package com.zyu.test;

import com.zyu.config.SystemConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-11 20:52
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfig.class)
@ActiveProfiles("dev") // 指定运行测试时需要激活哪一个Profile
public class Test {
    @Autowired
    private DataSource dataSource;

    @org.junit.Test
    public void test() throws SQLException {
//        System.out.println(dataSource);
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//        PreparedStatement statement = connection.prepareStatement("select name from t_user  where id = ?");
//        statement.setInt(1,2);
//        ResultSet resultSet = statement.executeQuery();
//        if(resultSet.next()) {
//            String name = resultSet.getString("name");
//            System.out.println(name);
//        }
    }
}
