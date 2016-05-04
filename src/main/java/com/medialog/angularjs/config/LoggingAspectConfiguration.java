package com.medialog.angularjs.config;

import com.medialog.angularjs.aop.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by ASH on 2016-05-03.
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
