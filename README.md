# 第三章：bean的高级装配

## 一：Spring Profile
    用于转换不同的开发环境，比如数据库配置

## 二：条件化的创建Bean
    配置在指定条件下创建bean
## 三：自动装配中的歧义性
    解决多个接口的实现类注入产生的引用不唯一问题：
    * 标识首选bean
    * 使用限定符
    情景：有一个接口Dessert，实现类Cake、Cookies、IceCream。注入Dessert的时候，由于有多个实现类，Spring无法在三个甜品中做出选择
        此时可以使用`@Primary`标识首选，可以和@Component一起放在类上，也可以在配置类中和@Bean放在方法上，那么问题又来了，
        当有多个@primary标识呢？还是无法做出选择,这个时候就需要使用限定符`@Qualifier`
### @Qualifier限定符
    最简单的例子：想要把IceCream注入到setDessert()中
    ```java
    @Autowrited
    @Qualifier("iceCream) // 想要注入到 Dessert 中的bean的id
    public void setDessert(Dessert dessert){
        this.dessert = dessert;
    }
    ```
    一个细节：@Qualifier("iceCream)所引用的bean需要具有String类型的“iceCream”作为限定符，如果没有指定限定符，Spring会默认给容器中
    的对象设置限定符，问题：当重构类名之后，限定符也随着改变，那么这里的@Qualifier就找不到指定iceCream的bean了。
    解决方法：
    一：放入容器的时候指定id
    @Component("clod")
    二：创建自定义限定符，例如
    ```java
    @Component
    @Qualfier("clod") //自定义限定符id，解耦和，也可以和@Bean组合使用
    public void IceCream implements Dessert(){//...}
    ```
    三：使用自定义的限定符注解
    同样的问题，如果其他的实现类也具有"clod"特性，也使用了@Qualfier("clod")作为限定符，再次出现歧义😰！
    自定义注解语法：
    
         
## 四：bean的作用域  singleton、prototype、session、request
## 五：spring表达式语言