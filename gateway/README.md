# 项目遇到的问题统计:

## 1.断言成功匹配但是获取服务503

### 解决方法：新版本的SpringCloud Nacos等弃用了旧版的Ribbon等 所以需要引入Feign以及LoadBalence依赖来解决问题

```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

