package ir.webold.framework.config.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        Parameter headerParam = new ParameterBuilder().name("ApiKey").parameterType("header")
                .modelRef(new ModelRef("string")).description("ApiKey").required(true).build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).globalOperationParameters(Arrays.asList(headerParam)).select()
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
