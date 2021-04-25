package com.webold.core.config.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Value("${thread.core.poolSize}")
    private Integer corePollSize;

    @Value("${thread.core.max.poolSize}")
    private Integer maxPoolSize;

    @Value("${thread.core.query.capacity}")
    private Integer queryCapacity;

    @Bean("treadPoolAsync")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePollSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queryCapacity);
        executor.setThreadNamePrefix("AsyncMsgExecutor-");
        executor.initialize();
        return executor;
    }

}
