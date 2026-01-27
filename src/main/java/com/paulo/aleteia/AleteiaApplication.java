package com.paulo.aleteia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AleteiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AleteiaApplication.class, args);
	}

}
