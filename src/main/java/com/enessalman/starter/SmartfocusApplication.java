package com.enessalman.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.enessalman"})
@ComponentScan(basePackages = {"com.enessalman"})
@EnableJpaRepositories(basePackages = {"com.enessalman"})
@EnableJpaAuditing
public class SmartfocusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartfocusApplication.class, args);
	}

}
