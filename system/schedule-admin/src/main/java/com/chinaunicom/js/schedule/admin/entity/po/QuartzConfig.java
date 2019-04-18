package com.chinaunicom.js.schedule.admin.entity.po;

import com.chinaunicom.js.common.core.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzConfig extends BasePo implements Serializable {

    private String quartzGroup;

    private String quartzName;

    private String cron;

    private Integer quartzStatus;

    private String msg;

    private String quartzClass;

    private Date startTime;

    private Date endTime;

}