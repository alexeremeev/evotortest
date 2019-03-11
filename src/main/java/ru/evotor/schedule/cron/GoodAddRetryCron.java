package ru.evotor.schedule.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import ru.evotor.service.GoodAddRetryService;

import javax.annotation.PostConstruct;

@Configuration
public class GoodAddRetryCron extends CronImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodAddRetryCron.class);

    @PostConstruct
    void init() {
        cronTriggerFactoryBean = goodAddRetryCronTriggerFactoryBean();
        jobDetailFactoryBean = goodAddRetryJobDetailFactory();
        register();
    }

    @Bean
    public JobDetailFactoryBean goodAddRetryJobDetailFactory() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(GoodAddRetryJob.class);
        jobDetailFactory.setName("GoodAddRetryCron");
        jobDetailFactory.setGroup("EvotorTest");
        return jobDetailFactory;

    }

    @Bean
    public CronTriggerFactoryBean goodAddRetryCronTriggerFactoryBean() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(goodAddRetryJobDetailFactory().getObject());
        cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?"); //every minute
        cronTriggerFactoryBean.setName("GoodAddRetryCronTrigger");
        cronTriggerFactoryBean.setGroup("EvotorTestJob");
        return cronTriggerFactoryBean;
    }

    private static class GoodAddRetryJob implements Job {

        @Autowired
        public GoodAddRetryService goodAddRetryService;

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try {
                LOGGER.info("GoodAddRetryJob started. Retrying good send to endpoints.");
                this.goodAddRetryService.retryGoodAddAll();
                LOGGER.info("Finished GoodAddRetryJob.");
            } catch (Exception e) {
                LOGGER.error("Error when running the Job: GoodAddRetryJob, the error is " + e.getMessage(), e);
            }
        }
    }
}
