package com.chinaunicom.js.schedule.service.impl;

import com.chinaunicom.js.common.core.tools.json.JSONUtil;
import com.chinaunicom.js.schedule.entity.po.QuartzConfig;
import com.chinaunicom.js.schedule.exception.ScheduleException;
import com.chinaunicom.js.schedule.service.IAsyncQuartzConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by GaoHaoYu on 2019/3/30.
 */
@Service
public class AsyncQuartzConfService implements IAsyncQuartzConfService {

    /**
     * 规范：REDIS中任务前缀名
     */
    private static final String TASK_KEY_PRE = "zt:task:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<QuartzConfig> queryJobs() throws ScheduleException {
        List<QuartzConfig> list = new ArrayList<>();
        Set<String> keys = stringRedisTemplate.keys(TASK_KEY_PRE + "*");
        keys.forEach(key -> {
            String quartzConfigJson = stringRedisTemplate.opsForValue().get(key);
            QuartzConfig quartzConfig = JSONUtil.toBean(quartzConfigJson, QuartzConfig.class);
            list.add(quartzConfig);
        });
        return list;
    }

    @Override
    public QuartzConfig queryById(long id) throws ScheduleException {
        String quartzConfigJson = stringRedisTemplate.opsForValue().get(TASK_KEY_PRE + id);
        return JSONUtil.toBean(quartzConfigJson, QuartzConfig.class);
    }
}
