package com.gl.procamp.bellkross.football.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

import static com.gl.procamp.bellkross.football.config.JpaConfig.EnvironmentVariables.*;

@Configuration
@PropertySource("bellkross.properties")
public class JpaConfig {

    private final Environment environment;

    public JpaConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty(DB_DRIVER_CLASS_NAME));
        dataSource.setUrl(environment.getProperty(DB_URL));
        dataSource.setUsername(environment.getProperty(DB_NAME));
        dataSource.setPassword(environment.getProperty(DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean(value = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.gl.procamp.bellkross.football.model");
        return emf;
    }

    public static class EnvironmentVariables {
        public static String DB_DRIVER_CLASS_NAME = "database.driverClassName";
        public static String DB_URL = "database.url";
        public static String DB_NAME = "database.user";
        public static String DB_PASSWORD = "database.password";
    }

}
