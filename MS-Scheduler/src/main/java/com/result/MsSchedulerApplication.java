package com.result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSchedulerApplication.class, args);
	}
}
