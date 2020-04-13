package com.result.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.springframework.web.client.RestTemplate;

public class QueryRestJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String queryUrl = "http://localhost:8762/gateway/ms-query/getResult";
			String result = restTemplate.getForObject(queryUrl, String.class);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
