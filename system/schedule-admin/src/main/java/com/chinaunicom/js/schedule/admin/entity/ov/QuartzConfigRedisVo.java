package com.chinaunicom.js.schedule.admin.entity.ov;

import com.chinaunicom.js.common.core.entity.vo.BaseVo;
import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by GaoHaoYu on 2019/4/1.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuartzConfigRedisVo extends BaseVo {

    private Long id;

    private String quartzGroup;

    private String quartzName;

    private String cron;

    private Integer quartzStatus;

    private String quartzClass;

    private String startTime;

    private String endTime;

    public QuartzConfigRedisVo(QuartzConfig quartzConfig) {
        this.id = quartzConfig.getId();
        this.quartzGroup = quartzConfig.getQuartzGroup();
        this.quartzName = quartzConfig.getQuartzName();
        this.cron = quartzConfig.getCron();
        this.quartzStatus = quartzConfig.getQuartzStatus();
        this.quartzClass = quartzConfig.getQuartzClass();
        this.startTime = DateUtil.format(quartzConfig.getStartTime(), "yyyyMMddHHmmss");
        this.endTime = DateUtil.format(quartzConfig.getEndTime(), "yyyyMMddHHmmss");
    }
}
