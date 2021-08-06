package app.ladderproject.core.config;

import app.ladderproject.core.config.general.GeneralStatic;
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

@Configuration
@PropertySource(value = "classpath:core-exceptions.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:core-warning.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
@ComponentScan(basePackages = {"app.ladderproject.core"})
public class Config {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(GeneralStatic.connectionTimeOut))
                .setReadTimeout(Duration.ofMillis(GeneralStatic.readTimeOut))
                .errorHandler(new RestTemplateResponseErrorHandler())
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
