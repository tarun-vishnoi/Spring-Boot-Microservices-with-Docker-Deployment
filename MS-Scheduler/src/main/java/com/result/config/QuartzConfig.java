package com.result.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.result.job.QueryRestJob;

@Configuration
public class QuartzConfig {

	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean(
			@Autowired @Qualifier("queryRestJob") JobDetailFactoryBean jobDetailFactoryBean,
			@Autowired @Qualifier("queryRestTrigger") CronTriggerFactoryBean cronTriggerFactoryBean) throws Exception {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		/*
		 * schedulerFactoryBean.setOverwriteExistingJobs(true);
		 * schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
		 * schedulerFactoryBean.setAutoStartup(true);
		 */
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		schedulerFactoryBean.setJobDetails(jobDetailFactoryBean.getObject());
		schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
		return schedulerFactoryBean;
	}

	@Bean("queryRestTrigger")
	public CronTriggerFactoryBean getCronTriggerFactory(
			@Autowired @Qualifier("queryRestJob") JobDetailFactoryBean jobDetailFactoryBean) {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
		cronTriggerFactoryBean.setCronExpression("0/3 * * * * ?"); // every 3 seconds --- hit query url
		cronTriggerFactoryBean.setName("QueryRestTrigger");
		return cronTriggerFactoryBean;

	}

	@Bean("queryRestJob")
	public JobDetailFactoryBean queryJobBean() throws Exception {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(QueryRestJob.class);
		jobDetailFactoryBean.setName("QueryRestJob");
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}
