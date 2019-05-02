package com.gl.procamp.bellkross.football.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gl.procamp.bellkross.football.web")
public class WebConfig {
}
