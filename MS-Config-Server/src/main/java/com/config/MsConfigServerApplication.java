package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsConfigServerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsConfigServerApplication.class);

	public static void main(String[] args) {
		LOGGER.info("MsConfigServerApplication :: main()");
		SpringApplication.run(MsConfigServerApplication.class, args);
	}
}
