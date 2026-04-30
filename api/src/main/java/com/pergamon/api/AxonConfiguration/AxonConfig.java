package com.pergamon.api.AxonConfiguration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }
}