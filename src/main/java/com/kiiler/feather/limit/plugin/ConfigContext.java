package com.kiiler.feather.limit.plugin;

import java.util.concurrent.TimeUnit;

/**
 * @author liujiabao
 * 限流上下文
 * @since 2024-12-05
 */
public class ConfigContext {
    /**
     * 每单位时间允许请求
     */
    private long rate;
    /**
     * 速率的单位时间
     */
    private long rateInterval;
    /**
     * 时间单位
     */
    private TimeUnit unit;

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public long getRateInterval() {
        return rateInterval;
    }

    public void setRateInterval(long rateInterval) {
        this.rateInterval = rateInterval;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }
}
