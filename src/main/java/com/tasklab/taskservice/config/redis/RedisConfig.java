package com.tasklab.taskservice.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
//@EnableCaching
public class RedisConfig {



//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        return RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(defaultCacheConfig())
//                .withInitialCacheConfigurations(Collections.singletonMap("groupCache", defaultCacheConfig()))
//                .transactionAware()
//                .build();
//    }
//
//    @Bean
//    public RedisCacheConfiguration defaultCacheConfig() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(10))
//                .disableCachingNullValues()
//
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
}
