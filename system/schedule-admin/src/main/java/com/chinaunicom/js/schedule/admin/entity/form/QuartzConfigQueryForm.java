package com.chinaunicom.js.schedule.admin.entity.form;

import com.chinaunicom.js.common.core.entity.form.BaseQueryForm;
import com.chinaunicom.js.schedule.admin.entity.param.QuartzConfigQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class QuartzConfigQueryForm extends BaseQueryForm<QuartzConfigQueryParam> {

    @ApiModelProperty(value = "任务名称")
    private String quartzName;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Past(message = "查询开始时间必须小于当前日期")
//    @ApiModelProperty(value = "查询开始时间")
//    private Date createdTimeStart;
//
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @Past(message = "查询结束时间必须小于当前日期")
//    @ApiModelProperty(value = "查询结束时间")
//    private Date createdTimeEnd;
}
