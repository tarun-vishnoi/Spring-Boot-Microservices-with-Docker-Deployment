package com.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZuulApplication.class);

	public static void main(String[] args) {
		LOGGER.info("ZuulApplication :: main()");
		SpringApplication.run(ZuulApplication.class, args);
	}

}
