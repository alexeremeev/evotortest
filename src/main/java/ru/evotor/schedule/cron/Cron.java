package ru.evotor.schedule.cron;

public interface Cron {

    public void register();

    public void reschedule(String cronExpression);

    public void pause();

    public void resume();

}
