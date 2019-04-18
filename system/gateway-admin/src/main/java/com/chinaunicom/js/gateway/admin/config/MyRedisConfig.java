package com.chinaunicom.js.gateway.admin.config;

import com.chinaunicom.js.common.web.redis.RedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class MyRedisConfig extends RedisConfig {
}