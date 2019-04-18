package com.chinaunicom.js.schedule.entity.po;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@ApiModel
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuartzConfig implements Serializable {

    private static final long serialVersionUID = -2310224897150482538L;

    private Long id;

    private String quartzGroup;

    private String quartzName;

    private Integer quartzStatus;

    private String cron;

    private String msg;

    private String quartzClass;

    private Date startTime;

    private Date endTime;

}