package com.imam.moneymate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoneymateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneymateApplication.class, args);
	}

}
