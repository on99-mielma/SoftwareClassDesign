# 好像是Spring的八股呢

1. ### SpringBoot Bean加载过程

   启动Spring IOC容器(管理着 Bean 的生命周期，控制着 Bean的依赖注入)这个步骤分为三个阶段:

   1. 利用**BeanFactory**配置元信息 元信息可以来自Spring过去支持最完善的**xml配置文件**，或者是其他形式的例如properties的**磁盘文件**，也可以是现在主流的**注解**，甚至是直接的代码**硬编码**
   
      **BeanFactory**是一个工厂类，它负责生产和管理Bean的一个工厂。在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。
   
      而FactoryBean在IOC容器的基础上给Bean的实现加上了一个简单工厂模式和装饰模式为IOC容器中Bean的实现提供了更加灵活的方式
   
   2. 需要把第一步的元信息在内存中表示，方法就是通过**BeanDefinitionReader**将元信息存入**BeanDefinition**类中，这个类中有单例原型标识符，标识用户定义的b还是来源于配置文件的还是Spring内部的Bean，Bean的父类名称，Bean的ClassName，Bean的作用域，Bean是否是懒加载，Bean所依赖的其他Bean名称，Bean是否可以自动注入，Bean是否为主要候选的Bean即有多个实现类时选取一个主实现类，创建该Bean的工厂类，创建该Bean的工厂方法，Bean构造方法参数值以及所有属性，Bean是否是单例是否是抽象类
   
   3. 将上面生成的**BeanDefinition**注册到**BeanDefinitionRegistry**并以键值对的形式存放其中键是特定的Bean定义的id值是对应的**BeanDefinition**
   
   4. （可选）**BeanFactoryPostProcessor**可以对上述存在**BeanDefinitionRegistry**中的**BeanDefinition**进行一定程度的修改与替换
   
   启动实例化阶段:
   
   1. 策略模式确定不同对象的创建策略（反射方式orCGlib字节码生成方式etc）然后为了统一对不同类型对象的访问为所有创建的Bean实例套进**BeanWrapper**中
   
   2. 为套上**BeanWrapper**的Bean实例设置属性和依赖对象，这里涉及一个依赖循环问题：
   
      首先，Spring单例对象的初始化大略分为三步：
   
      1. `createBeanInstance`：实例化bean，使用构造方法创建对象，为对象分配内存。
      2. `populateBean`：进行依赖注入。
      3. `initializeBean`：初始化bean。
   
      Spring为了解决单例的循环依赖问题，使用了三级缓存：
   
      `singletonObjects`：完成了初始化的单例对象map，bean name --> bean instance
   
      `earlySingletonObjects `：完成实例化未初始化的单例对象map，bean name --> bean instance
   
      `singletonFactories `： 单例对象工厂map，bean name --> ObjectFactory，单例对象实例化完成之后会加入singletonFactories。
   
      假如A依赖了B的实例对象，同时B也依赖A的实例对象。
   
      1. A首先完成了实例化，并且将自己添加到singletonFactories中
      2. 接着进行依赖注入，发现自己依赖对象B，此时就尝试去get(B)
      3. 发现B还没有被实例化，对B进行实例化
      4. 然后B在初始化的时候发现自己依赖了对象A，于是尝试get(A)，尝试一级缓存singletonObjects和二级缓存earlySingletonObjects没找到，尝试三级缓存singletonFactories，由于A初始化时将自己添加到了singletonFactories，所以B可以拿到A对象，然后将A从三级缓存中移到二级缓存中
      5. B拿到A对象后顺利完成了初始化，然后将自己放入到一级缓存singletonObjects中
      6. 此时返回A中，A此时能拿到B的对象顺利完成自己的初始化
   
      由此看出，属性注入的循环依赖主要是通过将实例化完成的bean添加到singletonFactories来实现的。而使用构造器依赖注入的bean在实例化的时候会进行依赖注入，不会被添加到singletonFactories中。比如A和B都是通过构造器依赖注入，A在调用构造器进行实例化的时候，发现自己依赖B，B没有被实例化，就会对B进行实例化，此时A未实例化完成，不会被添加到singtonFactories。而B依赖于A，B会去三级缓存寻找A对象，发现不存在，于是又会实例化A，A实例化了两次，从而导致抛异常。
      
   3. 检查各类接口的实现情况：
   
      1. 如果实现了BeanNameAware接口，Spring将Bean的Id传递给setBeanName()方法.
      2. 如果实现了BeanFactoryAware接口，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入
      3. 如果实现了ApplicationContextAware接口，Spring将调用Bean的setApplicationContext()方法，将Bean所在应用上下文引用传进来
      4. 如果实现了BeanPostProcessor接口，Spring将调用postProcessBeforeInitialization()方法
      5. 如果实现了InitializingBean接口或使用init-method声明了初始化方法，Spring将调用他们的afterPropertiesSet()方法
      6. 如果实现了BeanPostProcessor接口，Spring将调用postProcessAfterInittalization()方法
   
   4. Bean此时已经准备就绪，可以被应用程序使用，并将一直驻留在应用上下文中，直到应用上下文被销毁
   
   5. 如果实现了DisposableBean方法或者是用了destory-method声明销毁方法，Spring将调用destory()接口方法

2. ## 一个请求过来在Spring中发生了哪些事情？

   首先是用TCP向web服务器建立连接后进行http请求发送http报文

   然后web服务器收到报文后在内部处理这个http请求解析http报文

   一般性上，web服务器会创建HttpServletRequest与HttpServletResponse，把请求封装进前者，把响应封装入后者，在这两者中间调用Servlet中的service()方法处理请求信息。

   而在Spring中，也是会对http请求进行与一般性的Servlet中的service()方法的对http请求方法的实现即doGet(),doPost(),doOption()等，更重要的是Spring也会计算处理请求所消耗的时间，当前服务器运行所在的地区，获取请求的属性，检查是不是异步相应的请求，准备处理请求需要的上下文和参数后进行DispatchServlet类的doService()业务处理，这个业务处理中，会先检查是不是一个include请求，如果是，则获取包含的请求中的获取A请求内部B请求设定的Spring策略，而后是设置web应用上下文到请求中，设置本地解析器，设置主题解析器，设置主题(默认是WebApplicationContext)，将request与response保存到一个FlashMap中，将一个请求跟另一个请求关联起来，在重定向时使用。

   而后是doDispatch()请求，从request中获取WebAsyncManager用于检查当前请求是否正在异步处理，随后检查是不是multipart请求并将请求转化为MulitpartHttpServletRequest类型的请求，然后根据request中的url从hanlderMapping中获取封装了HandlerInterceptor的HandlerExecutionChain，如果不存在处理链则返回，反之从处理链中获取Handler然后寻找合适的HandlerAdapter，在获取请求方式，对GET类型请求进行特判处理，后调用applyPreHandle方法执行全部的前置拦截器，后执行对应的HandlerAdapter的Handler方法，拿到对应的视图，检查当前的请求是否正在异步处理，如果是的则直接放弃并返回，如果处理的结果返回的视图是空的则使用默认的视图，不为空则用处理的结果，调用applyPostHandle方法执行后置拦截器

3. ### @SpringBootApplication注解

   除去java.lang.annotation的四个元注解

   ```
   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   ```

   1. 首先是

      ```
      @SpringBootConfiguration
      ```

      源码中的注释里是这样说的:

      指示类提供Spring Boot application@Configuration。可以用作Spring的标准@Configuration注释的替代品，以便可以自动找到配置（例如在测试中）。

      应用程序应该只包含一个@SpringBootConfiguration，大多数惯用的SpringBoot应用程序将从@SpringBootApplication继承它

   2. 第二个是

      ```
      @EnableAutoConfiguration
      ```

      导入spring配置文件中EnableAutoConfiguration所对应的所有类，涉及上文提到的BeanDefinition类

   3. 第三个是

      ```
      @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
      		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
      ```

      相当于告诉Spring这个package中用注解标识的类会被Spring自动扫描并装入IoC容器中，且自动扫描并加载符合条件的组件（如@Component与@Repository），或bean，最终将这些bean加载进IoC容器。说白了这就是将启动类放入root package中的原因，可以对同目录中的子包进行扫描.
   
4. ### Spring事务

   事务属性：

   1. 事务传播行为：

      ```
      TransactionDefinition.PROPAGATION_REQUIRED
      //如果当前没有事务，就新建一个事务，如果已经存在于一个事务中，则加入到这个事务中
      TransactionDefinition.PROPAGATION_SUPPORTS
      //支持当前事务，如果当前没有事务，就以非事务的方式执行
      TransactionDefinition.PROPAGATION_MANDATORY
      //使用当前的事务，如果当前没有事务，就抛出异常
      TransactionDefinition.PROPAGATION_REQUIRES_NEW
      //新建事务，如果当前存在事务，就把当前事务挂起
      TransactionDefinition.PROPAGATION_NOT_SUPPORTED
      //以非事务的方式执行操作，如果当前存在事务，就把当前事务挂起
      TransactionDefinition.PROPAGATION_NEVER
      //以非事务的方式执行操作，如果当前存在事务，则抛出异常
      TransactionDefinition.PROPAGATION_NESTED
      //如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与TransactionDefinition.PROPAGATION_REQUIRED类似的操作
      ```

   2. 事务隔离级别

      ```
      TransactionDefinition.ISOLATION_DEFAULT
      //使用后端数据库默认的隔离级别
      TransactionDefinition.ISOLATION_READ_UNCOMMITTED
      //最低的隔离级别，允许读取尚未提交的数据变更，（会导致脏读，幻读，不可重复读）
      TransactionDefinition.ISOLATION_READ_COMMITTED
      //允许读取并发事务已经提交的值，阻止脏读，（会导致幻读或不可重复读）
      TransactionDefinition.ISOLATION_REPEATABLE_READ
      //对同一字段的多次读取结果是一致的，除非数据是被本身事务自己所修改，阻止脏读和不可重复读，）可导致幻读）
      TransactionDefinition.ISOLATION_SERIALIZABLE
      //最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，阻止脏读，幻读，不可重复读，但严重影响性能
      ```

   3. 事务超时属性

      所谓事务超时，就是指一个事务所允许执行的最长时间，如果超过该时间限制但事务还没有完成，则自动回滚事务。以 int 的值来表示超时时间，其单位是秒，默认值为-1，这表示事务的超时时间取决于底层事务系统或者没有超时时间。

   4. 事务只读属性

      对于只有读取数据查询的事务，可以指定事务类型为 readonly，即只读事务。只读事务不涉及数据的修改，数据库会提供一些优化手段，适合用在有多条数据库查询操作的方法中

      - 如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持 SQL 执行期间的读一致性；
      - 如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询 SQL 必须保证整体的读一致性，否则，在前条 SQL 查询之后，后条 SQL 查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，此时，应该启用事务支持

   5. 事务回滚规则

      这些规则定义了哪些异常会导致事务回滚而哪些不会。默认情况下，事务只有遇到运行期异常（`RuntimeException` 的子类）时才会回滚，`Error` 也会导致事务回滚，但是，在遇到检查型（Checked）异常时不会回滚。

   @Transactional注解

   1. 只能用于public方法上
   2. 如果使用在类上，对类中所有的public方法都生效
   3. 不推荐在接口上使用

5. 