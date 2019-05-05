package com.gl.procamp.bellkross.football.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

@EnableWebMvc
@Configuration
@EnableJpaRepositories(value = "com.gl.procamp.bellkross.football")
@EnableTransactionManagement
@ComponentScan("com.gl.procamp.bellkross.football")
public class JpaConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return createEntityManagerFactory("FootballProperties");
    }

    @Bean("transactionManager")
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new JpaTransactionManager();
    }

}
