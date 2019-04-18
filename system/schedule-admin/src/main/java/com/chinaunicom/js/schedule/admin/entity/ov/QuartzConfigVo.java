package com.chinaunicom.js.schedule.admin.entity.ov;

import com.chinaunicom.js.common.core.entity.vo.BaseVo;
import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by GaoHaoYu on 2019/4/1.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuartzConfigVo extends BaseVo {

    private Long id;

    private String quartzGroup;

    private String quartzName;

    private String cron;

    private Integer quartzStatus;

    private String quartzClass;

    private String startTime;

    private String endTime;

    private String createdBy;

    private String updatedBy;

    private String createdTime;

    private String updatedTime;

    public QuartzConfigVo(QuartzConfig quartzConfig) {
        this.id = quartzConfig.getId();
        this.quartzGroup = quartzConfig.getQuartzGroup();
        this.quartzName = quartzConfig.getQuartzName();
        this.cron = quartzConfig.getCron();
        this.quartzStatus = quartzConfig.getQuartzStatus();
        this.quartzClass = quartzConfig.getQuartzClass();
        this.startTime = DateUtil.format(quartzConfig.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        this.endTime = DateUtil.format(quartzConfig.getEndTime(), "yyyy-MM-dd HH:mm:ss");
        this.createdBy = quartzConfig.getCreatedBy();
        this.updatedBy = quartzConfig.getUpdatedBy();
        this.createdTime = DateUtil.format(quartzConfig.getCreatedTime(), "yyyy-MM-dd HH:mm:ss");
        this.updatedTime = DateUtil.format(quartzConfig.getUpdatedTime(), "yyyy-MM-dd HH:mm:ss");
    }
}
