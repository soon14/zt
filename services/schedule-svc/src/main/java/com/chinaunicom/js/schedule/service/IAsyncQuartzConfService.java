package com.chinaunicom.js.schedule.service;

import com.chinaunicom.js.schedule.entity.po.QuartzConfig;
import com.chinaunicom.js.schedule.exception.ScheduleException;

import java.util.List;

/**
 * Created by GaoHaoYu on 2019/3/30.
 */
public interface IAsyncQuartzConfService {

    /**
     * 获取job列表
     * @return
     * @throws ScheduleException
     */
    List<QuartzConfig> queryJobs () throws ScheduleException;

    /**
     * 根据ID查询调度规则
     * @param id
     * @return
     * @throws ScheduleException
     */
    QuartzConfig queryById (long id) throws ScheduleException;
}
