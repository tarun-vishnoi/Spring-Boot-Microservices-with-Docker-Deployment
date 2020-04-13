package com.result.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResult {

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	@RequestMapping(value = "/result")
	public String printResult() {
		return "Hi ! Result from instance --> " + instanceId;
	}
}
