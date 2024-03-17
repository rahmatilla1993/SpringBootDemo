package com.example.springbootdemo.async_execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Slf4j
public class GlobalAsyncConfig implements AsyncConfigurer {

    private final int threadCount = Runtime.getRuntime().availableProcessors();

    @Override
    public Executor getAsyncExecutor() {
        var taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(threadCount);
        taskExecutor.setMaxPoolSize(50);
        taskExecutor.setQueueCapacity(30);
        taskExecutor.setKeepAliveSeconds(20);
        taskExecutor.setThreadNamePrefix("my-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return ((ex, method, params) -> {
            log.error("Exception occurred: In method: {}, with parameters: {}", method.getName(), params);
            ex.printStackTrace();
        });
    }
}
