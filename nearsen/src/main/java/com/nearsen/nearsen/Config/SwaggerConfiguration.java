package com.nearsen.nearsen.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){

        Contact contact = new Contact("邻感-宋敏","http://192.168.10.224:8080/swagger-ui.html","songmin@nearsen.cn");

        return new ApiInfo(
                "后台API接口",// 大标题
                "API接口",//小标题
                "0.0.1",//版本
                "www.nearsen.cn",
                contact,// 作者
                "后台",// 链接显示文字
                "http://www.nearsen.cn"
                );
    }
}

