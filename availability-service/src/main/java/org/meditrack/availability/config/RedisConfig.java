package org.meditrack.availability.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()

                // TTL configuration
                .entryTtl(Duration.ofMinutes(5))

                // Disable caching null values
                .disableCachingNullValues();

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

}
