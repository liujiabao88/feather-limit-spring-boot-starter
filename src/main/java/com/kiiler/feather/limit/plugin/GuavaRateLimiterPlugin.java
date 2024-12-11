package com.kiiler.feather.limit.plugin;

import com.google.common.util.concurrent.RateLimiter;
import com.kiiler.feather.limit.RateLimiterPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujiabao
 * guava限流实现
 * @since 2024-12-05
 */
public class GuavaRateLimiterPlugin implements RateLimiterPlugin {

    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Override
    public boolean acquire(String key, ConfigContext config) {
        double permitsPerSecond = (double) config.getRate() / config.getUnit().toSeconds(config.getRateInterval());
        RateLimiter rateLimiter = rateLimiterMap.computeIfAbsent(key, k -> RateLimiter.create(permitsPerSecond));

        if (rateLimiter.getRate() != permitsPerSecond) {
            rateLimiter.setRate(permitsPerSecond);
        }

        return rateLimiter.tryAcquire();
    }
}
