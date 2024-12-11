package com.kiiler.feather.limit;

import com.kiiler.feather.limit.type.RateLimiterType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author liujiabao
 * 限流注解
 * @since 2024-12-02
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 限流资源名称(唯一)
     * @return 限流资源名称
     */
    String sourceName();

    /**
     * 每单位时间允许请求
     * @return 每单位时间允许请求
     */
    long rate() default 5;

    /**
     * 速率的单位时间
     * @return 速率的单位时间
     */
    long rateInterval() default 1;

    /**
     * 时间单位
     * @return 时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 限流模式类型
     * @return 限流模式类型
     */
    RateLimiterType type() default RateLimiterType.REDISSON;

    /**
     * 动态配置项的key（优先获取动态配置）
     * 配置模版
     * {"rate":3,"rateInterval":1,"unit":"SECONDS"}
     * @return 动态配置项的key
     */
    String configKey() default "";
}
