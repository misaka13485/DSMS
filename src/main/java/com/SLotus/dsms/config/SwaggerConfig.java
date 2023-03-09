package com.SLotus.dsms.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    //是否允许显示swagger。此值可在application.yml中设定。
    //作为开关，可在生产环境和开发环境打开或关闭，简便易行。

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new java.util.ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder userIdPar = new ParameterBuilder();
        userIdPar.name("userId").description("用户ID").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(userIdPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.SLotus.dsms.controller")) //指定扫描包
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                //只显示api路径下的页面
                //.paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build()
                .globalOperationParameters(pars); //添加全局参数
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("药房管理系统Apt")
                .description("药房管理系统后台Api调试文档")
                .version("ver1.0.0")
                .build();
    }
}
