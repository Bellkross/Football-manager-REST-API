package com.gl.procamp.bellkross.football.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    private static String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/football";
    private static String DB_NAME = "postgres";
    private static String DB_PASSWORD = "root";

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_NAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
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

}
