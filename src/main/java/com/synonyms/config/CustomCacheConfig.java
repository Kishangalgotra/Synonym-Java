package com.synonyms.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableCaching
public class CustomCacheConfig {

    public SimpleCacheManager cacheManager;

    @Bean
    public CacheManager cacheManager() {
        cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("applicationCache"));
        caches.add(new ConcurrentMapCache("newCache"));

        cacheManager.setCaches(caches);
        return cacheManager;
    }

    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }
}
