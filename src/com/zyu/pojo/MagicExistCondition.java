package com.zyu.pojo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created with IntelliJ IDEA.
 * User: z.yu
 * DateTime: 2018-04-12 10:57
 * Description: 校验Magic类是否存在的判断类
 */
public class MagicExistCondition implements Condition {
    /***
     * 执行条件对比，如果返回true，创建带有@Condictional(MagicExistCondition.class)的Bean，否则，不创建。
     * @param conditionContext 可以返回bean的相关信息
     * @param annotatedTypeMetadata 可以获取到该bean上面的其他注解
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        System.setProperty("magic","magic");//给环境添加magic属性，这样match方法返回true
        Environment environment = conditionContext.getEnvironment();//获得环境属性对象
        return environment.containsProperty("magic");//返回环境中是否包含magic属性
    }
}
