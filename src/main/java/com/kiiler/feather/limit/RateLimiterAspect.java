package com.kiiler.feather.limit;

import com.alibaba.fastjson2.JSON;
import com.kiiler.feather.limit.plugin.ConfigContext;
import com.kiiler.feather.limit.plugin.RateLimitException;
import com.kiiler.feather.limit.plugin.RateLimiterPluginFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author liujiabao
 * @description:
 * @since 2024-12-02
 */
@Aspect
public class RateLimiterAspect {

    private static final Log logger = LogFactory.getLog(RateLimiterAspect.class);

    private static final String RATE_LIMIT_KEY_PRE = "feather_limit_";

    @Autowired
    private Environment environment;

    @Autowired
    private RateLimiterPluginFactory pluginFactory;

    @Before("@annotation(rateLimiter)")
    public void rateLimit(RateLimiter rateLimiter) {
        if (pluginFactory == null || rateLimiter == null || StringUtils.isEmpty(rateLimiter.sourceName())) {
            return;
        }

        boolean acquired = true;
        try {
            ConfigContext config = getConfig(rateLimiter);
            RateLimiterPlugin plugin = pluginFactory.getRateLimiter(rateLimiter.type());
            acquired = plugin.acquire(generateKey(rateLimiter), config);
        } catch (Exception e) {
            logger.error("[feather-limit]Traffic limiting rule processing exception!", e);
        }

        if (!acquired) {
            throw new RateLimitException("请求过于频繁，请稍后再试");
        }
    }

    private ConfigContext getConfig(RateLimiter rateLimiter) {
        ConfigContext context = new ConfigContext();

        if (StringUtils.isNotBlank(rateLimiter.configKey())) {
            String configStr = environment.getProperty(rateLimiter.configKey());
            if (StringUtils.isNotBlank(configStr)) {
                return JSON.parseObject(configStr, ConfigContext.class);
            }
        }

        context.setRate(rateLimiter.rate());
        context.setRateInterval(rateLimiter.rateInterval());
        context.setUnit(rateLimiter.unit());
        return context;
    }

    private static String generateKey(RateLimiter rateLimiter) {
        return String.format(RATE_LIMIT_KEY_PRE + rateLimiter.configKey());
    }
}
