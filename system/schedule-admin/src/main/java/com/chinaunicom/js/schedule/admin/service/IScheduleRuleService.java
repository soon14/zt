package com.chinaunicom.js.schedule.admin.service;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigForm;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigQueryForm;
import com.chinaunicom.js.schedule.admin.entity.param.QuartzConfigQueryParam;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;

import java.util.List;

public interface IScheduleRuleService {

    /**
     * 获取调度任务
     *
     * @param id
     * @return
     */
    Result get(long id);

    /**
     * 新增调度任务
     *
     * @param quartzConfigForm
     * @return
     */
    Result add(QuartzConfigForm quartzConfigForm);

    /**
     * 查询调度任务
     *
     * @return
     */
    Result queryByConditions(QuartzConfigQueryForm quartzConfigQueryForm);

    /**
     * 根据name查询调度任务
     *
     * @return
     */
    Result query(String name);

    /**
     * 更新调度任务信息
     *
     * @param quartzConfigForm
     */
    Result update(long id, QuartzConfigForm quartzConfigForm);

    /**
     * 根据id删除调度任务
     *
     * @param id
     */
    Result delete(long id);

    /**
     * 重新加载调度任务配置到redis
     * @return 成功返回true
     */
    Result overload();
}
