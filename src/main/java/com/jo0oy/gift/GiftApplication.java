package com.jo0oy.gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftApplication.class, args);
	}

}
