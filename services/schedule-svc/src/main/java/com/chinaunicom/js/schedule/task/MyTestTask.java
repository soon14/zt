package com.chinaunicom.js.schedule.task;

import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.log.LoggerHelp;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by GaoHaoYu on 2019/3/30.
 */
public class MyTestTask implements Job, Serializable {

    private static final long serialVersionUID = -105728469141950541L;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LoggerHelp.info("xxxxxxxx1");
        } catch (Exception e) {
            LoggerHelp.error("==== 定时任务实现类 MyTestTask ====>异常!", e.getMessage());
        }
    }
}
