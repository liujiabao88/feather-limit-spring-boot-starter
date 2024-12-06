package com.kiiler.feather.limit.plugin;

import com.kiiler.feather.limit.RateLimiterPlugin;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateLimiterConfig;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiabao
 * @description:
 * @since 2024-12-05
 */
public class RedissonRateLimiterPlugin implements RateLimiterPlugin {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean acquire(String key, ConfigContext config) {
        final long rate = config.getRate();
        final long rateInterval = config.getRateInterval();
        final TimeUnit unit = config.getUnit();

        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        if (!rateLimiter.isExists()) {
            /** 配置初始化 */
            rateLimiter.trySetRate(RateType.OVERALL, rate, rateInterval, convertToRateIntervalUnit(unit));
        } else {
            RateLimiterConfig rateLimiterConfig = rateLimiter.getConfig();
            /** 配置重置 */
            if (rateLimiterConfig.getRateInterval() != unit.toMillis(rateInterval) || rateLimiterConfig.getRate() != rate) {
                rateLimiter.delete();
                rateLimiter.trySetRate(RateType.OVERALL, rate, rateInterval, convertToRateIntervalUnit(unit));
            }
        }

        return rateLimiter.tryAcquire();
    }

    private org.redisson.api.RateIntervalUnit convertToRateIntervalUnit(TimeUnit unit) {
        switch (unit) {
            case SECONDS:
                return org.redisson.api.RateIntervalUnit.SECONDS;
            case MINUTES:
                return org.redisson.api.RateIntervalUnit.MINUTES;
            case HOURS:
                return org.redisson.api.RateIntervalUnit.HOURS;
            case DAYS:
                return org.redisson.api.RateIntervalUnit.DAYS;
            default:
                throw new IllegalArgumentException("Unsupported TimeUnit: " + unit);
        }
    }
}
