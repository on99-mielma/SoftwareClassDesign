# 项目遇到的问题统计:

## 1.在不同微服务之间传递Mono<T>出现报错:Cannot construct instance of `reactor.core.publisher.Mono` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information

### 解决方法：在FeignClient处直接对Mono进行解包即直接以泛型对应的类作为返回类在最后可以重新打包回去







# 思考:

## 1.Feign作用是否算是微服务之间的通信？
