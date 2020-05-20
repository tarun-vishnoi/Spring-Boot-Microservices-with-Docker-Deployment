package com.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

public class QueryRestJob extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryRestJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String queryUrl = "http://localhost:8762/gateway/ms-query/getResult";
			String response = restTemplate.getForObject(queryUrl, String.class);
			LOGGER.info("==>> " + response + " <<==");
		} catch (Exception e) {
			LOGGER.error("QueryRestJob :: executeInternal() :: ERROR");
		}
	}
}
