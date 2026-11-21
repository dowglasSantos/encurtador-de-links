package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class EncurtadorDeLinksApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtadorDeLinksApplication.class, args);
	}

}
