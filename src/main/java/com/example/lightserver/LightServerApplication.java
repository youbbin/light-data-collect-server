package com.example.lightserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LightServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightServerApplication.class, args);
	}

}
