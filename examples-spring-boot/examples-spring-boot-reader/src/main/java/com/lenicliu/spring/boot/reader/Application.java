package com.lenicliu.spring.boot.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@SpringBootApplication
@Import(RepositoryRestMvcConfiguration.class)
@EntityScan("com.lenicliu.spring.boot.reader.entity")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}