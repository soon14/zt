package com.chinaunicom.js.schedule.task;

import com.chinaunicom.js.schedule.entity.po.QuartzConfig;
import com.chinaunicom.js.schedule.exception.ScheduleException;
import com.chinaunicom.js.schedule.quartz.CustomScheduleFactory;
import com.chinaunicom.js.schedule.service.impl.AsyncQuartzConfService;
import com.chinaunicom.log.LoggerHelp;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.List;

/**
 * Created by GaoHaoYu on 2019/3/30.
 */
public class InitTask implements Job, Serializable {

    private static final long serialVersionUID = -105728469141950541L;

    @Autowired
    private CustomScheduleFactory customScheduleFactory;

    // 任务配置读取服务
    @Autowired
    private AsyncQuartzConfService asyncQuartzConfService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            List<QuartzConfig> jobList = asyncQuartzConfService.queryJobs();
            customScheduleFactory.checkDelete(jobList);
            jobList.forEach(quartzConfig -> {
                try {
                    customScheduleFactory.checkUpdate(quartzConfig.getId());
                } catch (ScheduleException | SchedulerException | ClassNotFoundException e) {
                    LoggerHelp.error(e.getMessage());
                }
            });

        } catch (Exception e) {
            LoggerHelp.error("==== 初始化定时任务 ====>异常!" + e.getMessage());
        }
    }
}
