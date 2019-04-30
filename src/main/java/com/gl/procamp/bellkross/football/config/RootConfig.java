package com.gl.procamp.bellkross.football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.gl.procamp.bellkross.football")
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new JpaTransactionManager();
    }

}
