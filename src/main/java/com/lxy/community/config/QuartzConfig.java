package com.lxy.community.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {


    private static final String VIEW_TASK_IDENTITY = "ViewTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(ViewTask.class).withIdentity(VIEW_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //.withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(1)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(VIEW_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}



