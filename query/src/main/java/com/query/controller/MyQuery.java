package com.query.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class MyQuery {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	private static final Logger LOGGER = LoggerFactory.getLogger(MyQuery.class);

	@RequestMapping(value = "/get-result")
	@HystrixCommand(fallbackMethod = "defaultResult")
	public String getResult() {
		String response = null;
		try {
			LOGGER.info("query ::  " + instanceId);
			String resultUrl = "http://zuul:8762/gateway/result/get-instance";
			response = restTemplate.getForObject(resultUrl, String.class);
			LOGGER.info("MyQuery :: getResult() " + response);
			return response;
		} catch (Exception e) {
			LOGGER.error("MyQuery :: getResult() " + e.getMessage());
		}
		return response;
	}

	public String defaultResult() {
		LOGGER.warn("MyQuery :: defaultResult() :: Fallback method from @HystrixCommand");
		return "DEFAULT - Result Service is currently down, Please try again later.";
	}
}