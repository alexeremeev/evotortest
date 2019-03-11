package ru.evotor.schedule.cron;

import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import ru.evotor.schedule.service.SchedulerService;

public abstract class CronImpl implements Cron {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronImpl.class);

    @Autowired
    private SchedulerService schedulerService;

    protected CronTriggerFactoryBean cronTriggerFactoryBean;
    protected JobDetailFactoryBean jobDetailFactoryBean;

    public void register(){
        schedulerService.register(jobDetailFactoryBean.getObject(), cronTriggerFactoryBean.getObject());
        LOGGER.info("Register trigger " +  cronTriggerFactoryBean.getObject().getKey() + " with schedule " + cronTriggerFactoryBean.getObject().getCronExpression());

    }

    public void reschedule( String cronExpression ) {
        try {
            CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
            try {
                cronTriggerImpl.setCronExpression(cronExpression);
            } catch (java.text.ParseException e) {
                LOGGER.error(e.getMessage(), e);
            }
            schedulerService.reschedule(cronTriggerImpl);

            LOGGER.info("Reschedule trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

        } catch (ParseException e) {
            LOGGER.error("Quartz Scheduler can not reschedule with the expression " + cronExpression + ". the error is " + e.getMessage(), e);

        }
    }

    public void pause() {

        try {
            CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
            schedulerService.pause(cronTriggerImpl);

            LOGGER.info("Pause trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

        } catch (Exception e) {
            LOGGER.error("Quartz Scheduler can not Pause the trigger, the error is " + e.getMessage(), e);

        }
    }

    public void resume() {

        try {
            CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
            schedulerService.resume(cronTriggerImpl);

            LOGGER.info("Pause trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

        } catch (Exception e) {
            LOGGER.error("Quartz Scheduler can not Pause the trigger, the error is " + e.getMessage(), e);

        }
    }

}
