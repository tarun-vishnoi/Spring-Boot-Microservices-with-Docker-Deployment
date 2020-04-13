package com.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class MyQuery {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/getResult")
	@HystrixCommand(fallbackMethod = "defaultResult")
	public String getResult() {
		String resultUrl = "http://localhost:8762/gateway/ms-result/result";
		String result = restTemplate.getForObject(resultUrl, String.class);
		return result;
	}

	public String defaultResult() {
		return "Result Service is currently down, Please try again later.";
	}

}
