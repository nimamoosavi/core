package ir.webold.framework.config.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/posts.*"), regex(".*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("LiveHesab")
                .description("LiveHesab API reference for developers")
                .license("LiveHesab License")
                .version("1.1").build();
    }
}
