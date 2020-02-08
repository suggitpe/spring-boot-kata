package org.xpdojo.bank.account;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.xpdojo.bank.account.repository.AccountRepository;
import org.xpdojo.bank.account.repository.InMemoryAccountRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.WebApplicationType.SERVLET;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class BankAccountService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BankAccountService.class)
                .web(SERVLET)
                .run(args);
    }

    @Bean
    public AccountRepository createAccountRepository(){
        return new InMemoryAccountRepository();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(false);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(1000);
        return loggingFilter;
    }

}
