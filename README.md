**第三章：bean的高级装配**

**Spring Profile**:   用于转换不同的开发环境，比如数据库配置

**条件化的创建Bean**:  配置在指定条件下创建bean

**自动装配中的歧义性**: 多个接口的实现类注入产生的引用不唯一问题：
    （一）标识首选bean
    （二）使用限定符
    接口Dessert，实现类Cake、Cookies、IceCream。
    注入Dessert的时候，由于有多个实现类，Spring无法在三个甜品中做出选择（我也是，都太甜了）
    此时可以使用@Primary标识首选，可以和@Component一起放在类上，也可以在配置类中和@Bean放在方法上，
    那么问题又来了，当有多个@primary标识呢？还是无法做出选择
    
    
**bean的作用域**:  singleton、prototype、session、request

**spring表达式语言**
