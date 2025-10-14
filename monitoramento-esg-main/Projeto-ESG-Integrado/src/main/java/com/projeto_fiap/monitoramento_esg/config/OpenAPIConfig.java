package com.projeto_fiap.monitoramento_esg.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ESG de Monitoramento de Consumo de Energia")
                        .description("""
                        API REST para monitoramento de sensores de consumo energético e alertas de consumo excessivo.

                        Projeto com intuito acadêmico referente a um projeto da Faculdade de Informática e Administração Paulista (FIAP).

                        Contatos:
                        - Kaique Martins da Silva Ferreira: kaiquemartinsdasilvaferreira@gmail.com (RM557522)
                        - Henrique de Novaes: rick.novaes10@gmail.com (RM98485)
                        - Mauricio Dias Gonçalves: mauriciodiasgoncalves@gmail.com (RM555486)
                        """)
                        .version("1.0")
                        .contact(new Contact().name("Kaique Martins da Silva Ferreira").email("kaiquemartinsdasilvaferreira@gmail.com"))
                        .license(new License().name("Uso acadêmico")));
    }
}
