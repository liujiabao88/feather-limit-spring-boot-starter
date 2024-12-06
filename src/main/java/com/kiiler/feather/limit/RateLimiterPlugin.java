package com.kiiler.feather.limit;

import com.kiiler.feather.limit.plugin.ConfigContext;

/**
 * @author liujiabao
 * @description:
 * @since 2024-12-05
 */
public interface RateLimiterPlugin {
    /**
     * 获取许可
     * @param key 唯一标识符
     * @param config
     * @return
     */
    boolean acquire(String key, ConfigContext config);
}
