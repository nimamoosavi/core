package com.nicico.cost.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static com.nicico.cost.framework.config.general.GeneralStatic.connectionTimeOut;
import static com.nicico.cost.framework.config.general.GeneralStatic.readTimeOut;

@Configuration
@PropertySource(value = "classpath:applicationException.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@ComponentScan(basePackages = {"com.nicico.cost.framework"})
public class Config {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(connectionTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut))
                .build();
    }

    @Bean
    @LoadBalanced
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @LoadBalanced
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
