# 介绍
Spring Boot 中快速使用的轻量级限流的Starter
# 快速开始
## JDK版本
**1.8或1.8+**
## 添加依赖
```
<dependency>  
    <groupId>io.github.liujiabao88</groupId>  
	<artifactId>feather-limit-spring-boot-starter</artifactId>  
	<version>1.0.0</version>
</dependency>
```
## Redis配置（限集群模式）
- application.yml
```
spring:  
  redis:  
    host: xxxx  
    port: xxxx
    password: xxxx
```
- application.properties
```
spring.redis.host=xxxx
spring.redis.port=xxxx
spring.redis.password=xxxx
```
## 限流规则配置

> [!NOTE]
> 限流规则支持通过配置获取，配置Key可自定义

- application.properties
```
rate.limiter.test.config={"rate":3,"rateInterval":1,"unit":"SECONDS"}

```
## 注解实用

> [!NOTE]  
> **sourceName** - 限流资源名称(唯一)  
> **rate** - 单位时间允许请求  
> **rateInterval** - 速率的单位时间  
> **unit** - 时间单位  
> **type** - 限流模式类型  
> **configKey** - 动态配置项的key
### 单机模式
```
@RateLimiter(sourceName = "rateLimiterTest", rate = 10, rateInterval = 1, unit = TimeUnit.SECONDS, type = RateLimiterType.GUAVA)  
public void test() {  
    // do something
}
```
### 集群模式
```
@RateLimiter(sourceName = "rateLimiterTest", rate = 10, rateInterval = 1, unit = TimeUnit.SECONDS, type = RateLimiterType.REDISSON)  
public void test() {  
    // do something
}
```
### 动态配置
```
@RateLimiter(sourceName = "rateLimiterTest", configKey = "rate.limiter.test.config")  
public void test() {  
    // do something
}
```
