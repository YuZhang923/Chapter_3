# 第三章：bean的高级装配

## 一：Spring Profile
    用于转换不同的开发环境，比如数据库配置

## 二：条件化的创建Bean
    配置在指定条件下创建bean
## 三：自动装配中的歧义性
    解决多个接口的实现类注入产生的引用不唯一问题：
    情景：有一个接口Dessert，实现类Cake、Cookies、IceCream。注入Dessert的时候，由于有多个实现类，
    Spring无法在三个甜品中做出选择。此时可以使用`@Primary`标识首选
* 标识首选bean 
                
      @Primary可以和@Component一起放在类上，也可以在配置类中和@Bean放在方法上，那么问题又来了，
      当有多个@primary标识呢？还是无法做出选择,这个时候就需要使用限定符`@Qualifier`
* 使用限定符
  
### @Qualifier限定符
    最简单的例子：想要把IceCream注入到setDessert()中
   ```java
    @Autowrited
    @Qualifier("iceCream") // 想要注入到 Dessert 中的bean的id
    public void setDessert(Dessert dessert){
            this.dessert = dessert;
    }
   ```
    一个细节：@Qualifier("iceCream)所引用的bean需要具有String类型的“iceCream”作为限定符，
    如果没有指定限定符，Spring会默认给容器中的对象设置限定符，问题：当重构类名之后，限定符也随着改变，
    那么这里的@Qualifier就找不到指定iceCream的bean了。
    解决方法：
    一：放入容器的时候指定id
        @Component("clod")
    二：创建id的限定符，例如
   ```java
    @Component
    @Qualfier("clod") //自定义限定符id，解耦和，也可以和@Bean组合使用
    public class IceCream implements Dessert{}
   ```
    三：使用多个 @Qualifier() 缩小范围
        jdk1.8之前不允许在同一个条目上使用相同类型的注解，如果这样做编译会报错！，尽管jdk1.8之后支持
        使用重复注解，但前提是这个注解本身带有@Repeatable注解，而@Qualifier没有带有。因此，多个@Qualifier
        是行不通的。
    四：使用自定义的限定符注解
        同样的问题，如果其他的实现类也具有"clod"特性，也使用了@Qualfier("clod")作为限定符，再次出现歧义😰！
        自定义注解语法： 
   ```java
   @Targer
   @java.lang.annotation.Retention()
   @org.springframework.beans.factory.annotation.Qualifier 
   //自定义注解标识了Qualifier，本身具有了Qualifier的特性
   public @interface Clod{}
   ```
   放在类的上面
   ```java
   @Component
   @Clod // 使用自定义的注解，还可以用更多自定义注解修饰这个Bean...
   public class IceCream implements Dessert{}
   ```
   注入的时候
   ```java
    @Autowrited
    @Clod //上面的类使用多少个自定义限定符修饰，这里就用多少个
    public void setDessert(Dessert dessert){
        this.dessert = dessert;
    }
   ```
    
## 四：bean的作用域  singleton、prototype、session、request
* singleton、prototype不做笔记
* session

       情景：
            电商应用中，会有一个bean对应用户的购物车ShoppingCart，如果该购物车是singleton，那么会导致
            所有用户向同一个购物车添加商品；如果是prototype，那么一个地方添加到购物车到另一个地方就用不了，
            此时，session作用域是最好的选择
       小细节：
            如果要把session域的ShoppingCart注入到singleton域中的ShoppingService中时，ShoppingService
            会在应用上下文加载时创建，注入cart对象发现并没有！还有每个用户都拥有一个Cart，service中的
            cart不能是固定的，理想状态是service处理cart时所用的cart恰好是当前会话的那个cart！怎么做？
            
       这样声明session作用域：
```java
@Compoment
@org.springframework.context.annotation.Scope(
        value = "session",
        proxyMode = org.springframework.context.annotation.ScopedProxyMode.INTERFACES)
public class ShoppingCart {}
```
       实际上，Spring并不会将真正的ShoppingCart注入到Service中，注入的是ShoppingCart的代理对象，
       当service调用ShoppingCart的方法时，代理会对其进行懒解析并将调用委托给真正的ShoppingCart。
       proxyMode = ScopedProxyMode.INTERFACES 
            表示使用接口代理，ShoppingCart必须实现接口
       proxyMode = ScopedProxyMode.TARGER_CLASS
            表示使用CGLIB代理，不需要实现接口
* request
      
       同 session             
       
## 五：spring表达式语言
    情景：运行时值的注入
* 使用Environment获得属性值
    <br>API
    * String getProperty(String key) 通过属性名获得值
    * String getProperty(String key,String defaultValue) 通过属性名获得值，如果没有就返回默认值 
    * T getProperty(String key,Class<T> type) 通过属性名获得值,并指定转换的类型
    * T getProperty(String key,Class<T> type,T defaultValue) 通过属性名获得值，并指定转换的类型，如果没有就返回默认值
    * String getRequiredProperty(String key) 通过属性名获得值，要求该值必须被定义，如果没有则抛异常
    * Boolean containsProperty(String key) 检查某个属性是否存在
    * Class getPropertyAsClass("disc.class",CompactDisc.class) 将属性解析成类
* 使用Environment及属性占位符
        
        
    貌似只能通过xml的方式
    1.开启支持属性占位符（xml，javaConfig都可以）<context:property-placeholder>
    2.通过外部属性占位符注入创建bean 
        xml： <bean id="disc" class="com.zyu.pojo.BlankDisc" c:title="${disc.title}" c:_1="${disc.artist}"></bean>
                创建bean的同时注入了值
        javaConfig：在构造器的title、aitist参数前加上 @Value("${disc.title}")、@Value("${disc.artist}")
                构造器添加了@Value，但是怎么调用呢？ @Bean修饰的方法总得返回对象吧（retuen new BlankDisc()？，这样跟没注入有啥区别），
                就算是自动扫描调用的还是默认的构造器啊？！凉， 还是xml把。。 😔~
* 使用spEL表达式*
    ### 以强大和简洁的方式将值装配到 bean 的`属性`和构造器的`参数`中
       
    1.通过bean的id对bean进行引用
    
    2.调用方法以及引用对象中的属性
    
    3.计算表达式的值
    
    4.正则表达式的匹配
    
    5.集合的操作