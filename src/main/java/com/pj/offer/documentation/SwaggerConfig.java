package com.pj.offer.documentation;

import com.pj.offer.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pj.offer"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(User.class)
                .apiInfo(metaInfo())
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Authorization")
                                .description("Header para usar TOKEN")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()
                ));
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Projeto Shadow", "Microservice de oferta", "1.0", null,
                new Contact("Paulo Júnio de Oliveira", "https://nominalistic.wordpress.com/", "paulo.junio@compasso.com.br"),
                "Apache License", "https://www.apache.org/licenses/LICENSE-2.0.txt", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }

}
