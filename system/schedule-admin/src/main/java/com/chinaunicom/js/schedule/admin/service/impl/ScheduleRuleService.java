package com.chinaunicom.js.schedule.admin.service.impl;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.common.core.tools.json.JSONUtil;
import com.chinaunicom.js.schedule.admin.dao.ScheduleRuleMapper;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigForm;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigQueryForm;
import com.chinaunicom.js.schedule.admin.entity.ov.QuartzConfigRedisVo;
import com.chinaunicom.js.schedule.admin.entity.ov.QuartzConfigVo;
import com.chinaunicom.js.schedule.admin.entity.param.QuartzConfigQueryParam;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;
import com.chinaunicom.js.schedule.admin.service.IScheduleRuleService;
import com.chinaunicom.log.LoggerHelp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleRuleService implements IScheduleRuleService {

    /**
     * 规范：REDIS中任务前缀名
     */
    private static final String TASK_KEY_PRE = "zt:task:";

    @Autowired
    private ScheduleRuleMapper scheduleRuleMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result get(long id) {
        LoggerHelp.info("get with id:{}", id);
        QuartzConfig quartzConfig = scheduleRuleMapper.select(id);
        return Result.success(new QuartzConfigVo(quartzConfig));
    }

    @Override
    public Result add(QuartzConfigForm quartzConfigForm) {
        LoggerHelp.info("add quartz_config:{}", JSONUtil.toJsonStr(quartzConfigForm));
        QuartzConfig quartzConfig = quartzConfigForm.toPo(QuartzConfig.class);
        scheduleRuleMapper.insert(quartzConfig);
        stringRedisTemplate.opsForValue().set(TASK_KEY_PRE + quartzConfig.getId(), JSONUtil.toJsonStr(new QuartzConfigRedisVo(quartzConfig)));
        return Result.success(quartzConfig.getId());
    }

    @Override
    public Result queryByConditions(QuartzConfigQueryForm quartzConfigQueryForm) {
        Page page = PageHelper.startPage(quartzConfigQueryForm.getPageNum(), quartzConfigQueryForm.getPageSize());
        List<QuartzConfig> quartzConfigs = scheduleRuleMapper.query(quartzConfigQueryForm.toParam(QuartzConfigQueryParam.class));
        List<QuartzConfigVo> quartzConfigVos = quartzConfigs.stream().map(QuartzConfigVo::new).collect(Collectors.toList());
        PageInfo<QuartzConfigVo> pageInfo = new PageInfo<>(quartzConfigVos);
        pageInfo.setTotal(page.getTotal());
        LoggerHelp.info("pageInfo:", pageInfo);
        return Result.success(pageInfo);
    }

    @Override
    public Result query(String name) {
        List<QuartzConfigVo> quartzConfigVos = scheduleRuleMapper.query(new QuartzConfigQueryParam(name)).stream().map(QuartzConfigVo::new).collect(Collectors.toList());
        return Result.success(quartzConfigVos.stream().findFirst());
    }

    @Override
    public Result update(long id, QuartzConfigForm quartzConfigForm) {
        QuartzConfig quartzConfig = quartzConfigForm.toPo(QuartzConfig.class);
        quartzConfig.setId(id);
        scheduleRuleMapper.update(quartzConfig);
        stringRedisTemplate.delete(TASK_KEY_PRE + quartzConfig.getId());
        stringRedisTemplate.opsForValue().set(TASK_KEY_PRE + quartzConfig.getId(), JSONUtil.toJsonStr(new QuartzConfigVo(scheduleRuleMapper.select((quartzConfig.getId())))));
        return Result.success();
    }

    @Override
    public Result delete(long id) {
        scheduleRuleMapper.delete(id);
        stringRedisTemplate.delete(TASK_KEY_PRE + id);
        return Result.success();
    }

    @Override
    public Result overload() {
        List<QuartzConfig> quartzConfigs = scheduleRuleMapper.query(new QuartzConfigQueryParam());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        quartzConfigs.forEach(quartzConfig ->
                opsForValue.set(TASK_KEY_PRE + quartzConfig.getId(), JSONUtil.toJsonStr(new QuartzConfigVo(quartzConfig)))
        );
        return Result.success(true);
    }
}
