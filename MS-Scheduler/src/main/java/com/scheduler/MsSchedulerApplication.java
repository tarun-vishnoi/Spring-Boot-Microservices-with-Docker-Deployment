package com.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsSchedulerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsSchedulerApplication.class);

	public static void main(String[] args) {
		LOGGER.info("MsSchedulerApplication :: main()");
		SpringApplication.run(MsSchedulerApplication.class, args);
	}
}
