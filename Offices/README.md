# 项目遇到的问题统计:

## 1.复习mysql-canal-kafka-redis

1. 检查mysql中的binlog是否打开以及canal的conf文件中对应的binlog模式以及mysql中的canal用户密码设置

2. 对canal的配置文件进行设定与检查（关闭tsdb、指定log文件位置、指定destination、指定zookeeper的ip以及端口、指定kafka的ip和端口以及topic、根据JDK版本来更改cmd文件中的JVM设定例如取消永久代空间大小并修改成元空间大小）

3. 打开zookeeper并指定2181（or canal 中提前设置好的对应）端口

   ```
   //WINDOWS
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   ```

   

4. 打开kafka并指定log日志位置以及端口

   ```
   //WINDOWS   hospital.properties是我自己指定的
    .\bin\windows\kafka-server-start.bat .\config\hospital\hospital.properties
   ```

   

5. 在kafka中创建canal配置文件中需要的topic（if absent)

   ```
   //WINDOWS port:19911(kafka集群中的端口 勿用zookeeper指定的端口 会超出zk1MB限制)
   //--topic canalhospital 为我指定的topic
    .\bin\windows\kafka-topics.bat --create --bootstrap-server 127.0.0.1:19911 --replication-factor 1 --partitions 1 --topic canalhospital
   ```

6. 创建消费者用来测试

   ```
   //WINDOWS //最好指定相对应的partition
   .\bin\windows\kafka-console-consumer.bat --bootstrap-server 127.0.0.1:19911 -group test  --from-beginning --topic canalhospital
   ```

7. 开启canal服务器

8. 呼应第六步 测试是否正常使用

9. 在项目中配置kafka

   ```
     kafka:
       bootstrap-servers:  127.0.0.1:19911
       producer:
         key-serializer: org.apache.kafka.common.serialization.StringSerializer
         value-serializer: org.apache.kafka.common.serialization.StringSerializer
       #      value-serializer: org.apache.kafka.common.serialization.StringSerializer
       consumer:
         group-id: NacosInit
         auto-offset-reset: earliest
         enable-auto-commit: true
         auto-commit-interval: 1000
         #      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
         #      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
   
         key-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
         value-deserializer: org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
   
         properties:
           spring:
             json:
               trusted:
                 packages: "*"
             deserializer:
               key:
                 delegate:
                   class: org.apache.kafka.common.serialization.StringDeserializer
               value:
                 delegate:
                   class: org.apache.kafka.common.serialization.StringDeserializer
   ```

10. 在项目中写好相关的业务代码和kafka消息流的解析
11. 运行测试

## 2：在Zookeeper下运行Kafka The Cluster ID xxx doesn't match stored clusterId Some(yyy) in meta.properties

### 解决方法:清空log文件或者更改ClusterID

## 3: 项目中报错:CoordinatorNotAvailableException: The coordinator is not available.

### 解决方法:开启新的zookeeper存放新的node
