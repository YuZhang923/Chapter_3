package com.zyu.test;

import com.zyu.config.ExpressiveConfig;
import com.zyu.pojo.BlankDisc;
import com.zyu.pojo.SpELDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-13 19:11
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExpressiveConfig.class)
public class Test2 {
    @Resource
    private BlankDisc disc;

    @Autowired
    private SpELDemo spELDemo;

    @Test
    public void test() {
        System.out.println(disc);
    }

    //spEL表达式
    @Test
    public void test1() {
        System.out.println(spELDemo.getStr());
        System.out.println(spELDemo.getBlankDisc());
        System.out.println(spELDemo.getObj());
        System.out.println(spELDemo.isFalg());
    }
}
