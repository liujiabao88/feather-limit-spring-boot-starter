package com.kiiler.feather.limit.type;

import com.kiiler.feather.limit.plugin.GuavaRateLimiterPlugin;
import com.kiiler.feather.limit.plugin.RedissonRateLimiterPlugin;
import org.apache.commons.lang.StringUtils;

/**
 * @author liujiabao
 * 限流类型
 * @since 2024-12-05
 */
public enum RateLimiterType {
    REDISSON(StringUtils.uncapitalize(RedissonRateLimiterPlugin.class.getSimpleName())),
    GUAVA(StringUtils.uncapitalize(GuavaRateLimiterPlugin.class.getSimpleName()));

    private String beanKey;

    RateLimiterType(String beanKey) {this.beanKey = beanKey;}

    public String getBeanKey() {
        return beanKey;
    }
}
