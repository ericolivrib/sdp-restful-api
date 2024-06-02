package br.com.erico.tcc.sdp.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        var server = new Server().url("http://localhost:8080");

        // var contact = new Contact();

        // var license = new License();

        var info = new Info()
                .title("SDP | Sistema de Demandas de Projetos")
                .version("1.0")
                .description("API REST do sistema de Solicitação de Demandas em Projetos do Colégio Politécnico da UFSM");

        return new OpenAPI().info(info).servers(List.of(server));
    }

}
