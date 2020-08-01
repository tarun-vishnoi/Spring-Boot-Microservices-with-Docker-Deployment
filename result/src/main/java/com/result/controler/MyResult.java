package com.result.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResult {

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	private static final Logger LOGGER = LoggerFactory.getLogger(MyResult.class);

	@RequestMapping(value = "/get-instance")
	public String printResult() {
		LOGGER.info("MyResult :: printResult() :: " + instanceId);
		return " Result from instance --> " + instanceId;
	}
}