package com.kiiler.feather.limit;

import com.kiiler.feather.limit.plugin.GuavaRateLimiterPlugin;
import com.kiiler.feather.limit.plugin.RateLimiterPluginFactory;
import com.kiiler.feather.limit.plugin.RedissonRateLimiterPlugin;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liujiabao
 * 配置
 * @since 2024-12-05
 */
@Configuration
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableAspectJAutoProxy
public class FeatherLimitAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GuavaRateLimiterPlugin guavaRateLimiterPlugin() {
        return new GuavaRateLimiterPlugin();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonRateLimiterPlugin redissonRateLimiterPlugin() {
        return new RedissonRateLimiterPlugin();
    }

    @Bean
    @ConditionalOnMissingBean
    public RateLimiterPluginFactory rateLimiterPluginFactory() {
        return new RateLimiterPluginFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public RateLimiterAspect rateLimiterAspect() {
        return new RateLimiterAspect();
    }
}
