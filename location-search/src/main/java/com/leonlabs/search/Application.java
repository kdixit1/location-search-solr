package com.leonlabs.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.leonlabs.search.config.SolrConfig;

@SpringBootApplication
@Configuration
@Import(SolrConfig.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.leonlabs.search")
public class Application extends SpringBootServletInitializer {

	private static final Class<Application> applicationClass = Application.class;
	
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
