package com.kiiler.feather.limit;

/**
 * @author liujiabao
 * @description:
 * @since 2024-12-05
 */
@FunctionalInterface
public interface RateLimiterCallback {

    /**
     * 回调函数
     * @param rateLimiter
     */
    void callback(RateLimiter rateLimiter);
}
