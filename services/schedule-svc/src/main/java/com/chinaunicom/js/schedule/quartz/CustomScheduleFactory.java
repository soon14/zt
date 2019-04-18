package com.chinaunicom.js.schedule.quartz;

import com.chinaunicom.js.common.core.tools.core.date.DateTime;
import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.js.schedule.entity.po.QuartzConfig;
import com.chinaunicom.js.schedule.exception.ScheduleException;
import com.chinaunicom.js.schedule.service.impl.AsyncQuartzConfService;
import com.chinaunicom.log.LoggerHelp;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by GaoHaoYu on 2019/3/30.
 */
@Service
@Component
@Configuration
public class CustomScheduleFactory {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    // 任务配置读取服务
    @Autowired
    private AsyncQuartzConfService asyncQuartzConfService;

    private static Map<String, Map<String, Object>> runJobMap = new HashMap<>();

    private static final Long ID = 0L;

    private static final String GROUP = "init";

    private static final String NAME = "init";

    private static final Integer STATUS = 0;

    private static final String MSG = "init";

    @Value("${quartz.config.init.cron}")
    private String cron;

    @Value("${quartz.config.init.quartzClass}")
    private String quartzClass;

    public void scheduleJobs() throws SchedulerException, ScheduleException {
        Scheduler scheduler = getScheduler();
        startJob(scheduler);
    }

    /**
     * 获取scheduler
     * @return
     */
    private Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 项目启动 开启初始化任务
     * @param scheduler
     * @throws ScheduleException
     */
    @SuppressWarnings("unchecked")
    private void startJob(Scheduler scheduler) throws ScheduleException {
        try {
            QuartzConfig config = getInitTaskConfig();
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(config.getQuartzClass());
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(config.getQuartzName(), config.getQuartzGroup()).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(config.getCron());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(config.getQuartzName(), config.getQuartzGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (ClassNotFoundException | SchedulerException e) {
            LoggerHelp.error(e.getMessage());
        }
    }

    /**
     * 初始化初始任务配置（yml文件中配置）
     * @return
     */
    private QuartzConfig getInitTaskConfig () {
        QuartzConfig quartzConfig = new QuartzConfig();
        quartzConfig.setId(ID);
        quartzConfig.setCron(cron);
        quartzConfig.setQuartzGroup(GROUP);
        quartzConfig.setQuartzStatus(STATUS);
        quartzConfig.setMsg(MSG);
        quartzConfig.setQuartzName(NAME);
        quartzConfig.setQuartzClass(quartzClass);
        return quartzConfig;
    }

    /**
     * 任务暂停
     * @param id
     * @return
     * @throws SchedulerException
     * @throws ScheduleException
     */
    public boolean pauseJob(long id) throws SchedulerException, ScheduleException {
        Scheduler scheduler = getScheduler();
        QuartzConfig quartzConfig = asyncQuartzConfService.queryById(id);
        JobKey jobKey = JobKey.jobKey(quartzConfig.getQuartzName(), quartzConfig.getQuartzGroup());
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("status", 1);
        statusMap.put("cron", quartzConfig.getCron());
        statusMap.put("class", quartzConfig.getQuartzClass());
        runJobMap.put(jobKey.getGroup() + "." + jobKey.getName(), statusMap);
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 任务恢复
     * @param id
     * @return
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws ScheduleException
     */
    @SuppressWarnings("unchecked")
    public boolean resumeJob(long id) throws SchedulerException, ClassNotFoundException, ScheduleException {
        Scheduler scheduler = getScheduler();
        QuartzConfig quartzConfig = asyncQuartzConfService.queryById(id);
        JobKey jobKey = JobKey.jobKey(quartzConfig.getQuartzName(), quartzConfig.getQuartzGroup());
        JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
        if (jobDetail1 == null) {
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(quartzConfig.getQuartzClass());
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(quartzConfig.getQuartzName(), quartzConfig.getQuartzGroup()).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzConfig.getCron());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(quartzConfig.getQuartzName(), quartzConfig.getQuartzGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } else {
            scheduler.resumeJob(jobKey);
        }
        Map<String, Object> statusMap = new HashMap<>();
        statusMap.put("status", 0);
        statusMap.put("cron", quartzConfig.getCron());
        statusMap.put("class", quartzConfig.getQuartzClass());
        runJobMap.put(jobKey.getGroup() + "." + jobKey.getName(), statusMap);
        return true;
    }

    /**
     * 重启任务
     * @param id
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws ScheduleException
     */
    public void restartJob (long id) throws SchedulerException, ClassNotFoundException, ScheduleException {
        if (pauseJob(id)) {
            resumeJob(id);
        }
    }

    /**
     * 检查是否任务列表、状态是否有更新
     * @param id
     * @throws ScheduleException
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    public void checkUpdate (long id) throws ScheduleException, SchedulerException, ClassNotFoundException {
        QuartzConfig quartzConfig = asyncQuartzConfService.queryById(id);
        JobKey jobKey = JobKey.jobKey(quartzConfig.getQuartzName(), quartzConfig.getQuartzGroup());
        Boolean isExists = runJobMap.get(jobKey.getGroup() + "." + jobKey.getName()) != null;
        if (!isExists) {//如果不存在该job则新建定时任务并根据状态判断是否启动
            // 1-暂停的任务 0-正常运行任务
            if (0L == quartzConfig.getQuartzStatus() && checkTime(quartzConfig)) {
                resumeJob(id);
            }
        } else {
            Map<String, Object> statusMap = runJobMap.get(quartzConfig.getQuartzGroup() + "." + quartzConfig.getQuartzName());
            Integer status = Integer.parseInt(statusMap.get("status").toString());
            String cron = statusMap.get("cron").toString();
            String quartzClass = statusMap.get("class").toString();
            if (1L == quartzConfig.getQuartzStatus() || !checkTime(quartzConfig)) {//如果任务状态为暂停，则直接停止
                if (status == 0) {
                    pauseJob(id);
                }
            } else {
                boolean isRestart = false;
                //如果任务执行类和任务cron表达式都一致则跳过该任务，否则需要重启
                if (!quartzClass.equals(quartzConfig.getQuartzClass())) {
                    isRestart = true;
                    LoggerHelp.info("Task类已经修改为'" + quartzConfig.getQuartzClass() + "'. 老的Task类为:'" + quartzClass + "'");
                }
                if ( !cron.equals(quartzConfig.getCron())) {
                    isRestart = true;
                    LoggerHelp.info("Cron表达式已经修改为'" + quartzConfig.getCron() + "'. 老的Cron表达式为:'" + cron + "'");
                }
                if (isRestart) {
                    restartJob(id);
                }
                if (status == 1 && quartzConfig.getQuartzStatus() == 0) {
                    resumeJob(id);
                }
            }
        }
    }

    /**
     * 判断当前时间是否属于配置的时间段内
     * @param quartzConfig
     * @return
     */
    private boolean checkTime (QuartzConfig quartzConfig) {
        DateTime now = DateUtil.date();
        DateTime startTime = DateUtil.date(quartzConfig.getStartTime());
        DateTime endTime = DateUtil.date(quartzConfig.getEndTime());
        if (startTime.compareTo(now) <= 0 && endTime.compareTo(now) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 检查删除的任务并停止
     * @param jobList
     * @throws ScheduleException
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    public void checkDelete (List<QuartzConfig> jobList) throws ScheduleException, SchedulerException, ClassNotFoundException {
        Scheduler scheduler = getScheduler();
        runJobMap.keySet().forEach(jobDef -> {
            if (!checkIsExist(jobList, jobDef)) {//不存在则停止当前任务
                try {
                    String[] jobKeys = jobDef.split("\\.");
                    scheduler.deleteJob(JobKey.jobKey(jobKeys[1], jobKeys[0]));
                    runJobMap.remove(jobDef);
                } catch (SchedulerException ignored) {
                }
            }
        });
    }

    /**
     * 检查正在运行的任务在任务列表中是否存在
     * @param jobList
     * @param jobDef
     * @return
     */
    private boolean checkIsExist (List<QuartzConfig> jobList, String jobDef) {
        Boolean isExists;
        for (QuartzConfig quartzConfig : jobList) {
            isExists = jobDef.equals(quartzConfig.getQuartzGroup() + "." + quartzConfig.getQuartzName());
            if (isExists) {
                return true;
            }
        }
        return false;
    }
}
