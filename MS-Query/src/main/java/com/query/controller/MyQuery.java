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

	@RequestMapping(value = "/getResult")
	@HystrixCommand(fallbackMethod = "defaultResult")
	public String getResult() {
		LOGGER.info("MyQuery :: getResult() :: START");
		String resultUrl = "http://zuul:8762/gateway/ms-result/result";
		String msResultResponse = restTemplate.getForObject(resultUrl, String.class);
		LOGGER.info("MyQuery :: getResult() :: END");
		return msResultResponse;
	}

	public String defaultResult() {
		LOGGER.info("MyQuery :: defaultResult() :: Fallback method from @HystrixCommand");
		return "DEFAULT - Result Service is currently down, Please try again later.";
	}

}
