package com.acme.keeplo.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KeeploPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeeploPlatformApplication.class, args);
	}

}
