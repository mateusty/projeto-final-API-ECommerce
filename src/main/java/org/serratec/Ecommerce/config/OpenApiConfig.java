package org.serratec.Ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI config() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Servidor local - Pet Shop");

        Info info = new Info()
                .title("API PetShop Ecommerce")
                .description("""
                        API completa para gerenciamento do ecommerce de produtos e serviços pet,
                        incluindo cadastro de clientes, endereços e processamento de pedidos.
                       
                        Integrantes (Grupo 04): Gabriel Maia | Mateus Tamaki | Pedro Martins |
                        Caio Lukas | Liliane Costa.
                       """)
                .version("1.0.0");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}