package com.pergamon.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig 
{

    @Value("${app.swagger.server-url:}")
    private String serverUrl;

    @Bean
    public OpenAPI customOpenAPI()
    {
        OpenAPI openAPI = new OpenAPI();

        if (serverUrl != null && !serverUrl.isEmpty()) {
            openAPI.setServers(List.of(new Server().url(serverUrl).description("Production Server (HTTPS)")));
        }
        else
        {
            openAPI.setServers(List.of(new Server().url("/").description("Default Server Url")));
        }

        return openAPI;
    }
}
