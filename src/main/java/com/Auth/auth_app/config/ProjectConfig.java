package com.Auth.auth_app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
//Model Mapper is used to map the DTOs to the actual user entity in the database.
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
