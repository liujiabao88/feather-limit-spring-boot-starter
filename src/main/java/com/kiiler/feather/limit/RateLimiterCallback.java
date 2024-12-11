package com.kiiler.feather.limit;

/**
 * @author liujiabao
 * 回调函数
 * @since 2024-12-05
 */
@FunctionalInterface
public interface RateLimiterCallback {

    /**
     * 回调函数
     * @param rateLimiter 限流器对象
     */
    void callback(RateLimiter rateLimiter);
}
