package com.kiiler.feather.limit.plugin;

/**
 * @author liujiabao
 * 限流异常
 * @since 2024-12-05
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException(String msg) {
        super(msg);
    }
}
