package com.chinaunicom.js.schedule.quartz;

import com.chinaunicom.js.schedule.exception.ScheduleException;
import com.chinaunicom.log.LoggerHelp;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务运行工厂类
 * Created by GaoHaoYu on 2019/3/30.
 */
@Configuration
public class StartSchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CustomScheduleFactory customScheduleFactory;

    @Autowired
    private CustomJobFactory customJobFactory;

    // springboot 启动监听
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            customScheduleFactory.scheduleJobs();
        } catch (SchedulerException | ScheduleException e) {
            LoggerHelp.error(e.getMessage());
        }
    }

    //注入SchedulerFactoryBean
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(customJobFactory);
        return schedulerFactoryBean;
    }

}
