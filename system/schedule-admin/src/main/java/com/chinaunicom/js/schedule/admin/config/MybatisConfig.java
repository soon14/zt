package com.chinaunicom.js.schedule.admin.config;

import com.chinaunicom.js.common.web.interceptor.AuditInterceptor;
import com.chinaunicom.js.common.web.interceptor.SqlInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 初使化Mybatis审计字段自动赋值的interceptor
 */
@Configuration
@ComponentScan(basePackageClasses = {SqlInterceptor.class, AuditInterceptor.class})
public class MybatisConfig {
}
