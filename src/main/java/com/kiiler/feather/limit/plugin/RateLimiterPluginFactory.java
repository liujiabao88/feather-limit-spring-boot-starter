package com.kiiler.feather.limit.plugin;

import com.kiiler.feather.limit.RateLimiterPlugin;
import com.kiiler.feather.limit.type.RateLimiterType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author liujiabao
 * @description:
 * @since 2024-12-05
 */
public class RateLimiterPluginFactory {

    @Autowired
    private Map<String, RateLimiterPlugin> rateLimiterPlugins;

    public RateLimiterPlugin getRateLimiter(RateLimiterType type) {
        RateLimiterPlugin rateLimiterPlugin = rateLimiterPlugins.get(type.getBeanKey());
        if (rateLimiterPlugin == null) {
            throw new IllegalArgumentException("Unsupported RateLimiter type: " + type);
        }

        return rateLimiterPlugin;
    }
}
