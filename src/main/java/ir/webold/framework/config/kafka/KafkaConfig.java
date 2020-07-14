package ir.webold.framework.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class KafkaConfig {
    @Value("${kafka.thread.core.poolSize}")
    private Integer corePollSize;

    @Value("${kafka.thread.core.max.poolSize}")
    private Integer maxPoolSize;

    @Value("${kafka.thread.core.query.capacity}")
    private Integer queryCapacity;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePollSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queryCapacity);
        executor.setThreadNamePrefix("KafkaMsgExecutor-");
        executor.initialize();
        return executor;
    }

}
