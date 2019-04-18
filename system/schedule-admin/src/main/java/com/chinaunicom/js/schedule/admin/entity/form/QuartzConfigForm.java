package com.chinaunicom.js.schedule.admin.entity.form;

import com.chinaunicom.js.common.core.entity.form.BaseForm;
import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.js.schedule.admin.entity.po.QuartzConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class QuartzConfigForm extends BaseForm<QuartzConfig> {

    @NotEmpty(message = "任务所属组不能为空")
    @ApiModelProperty(value = "任务所属组")
    private String quartzGroup;

    @NotEmpty(message = "任务名称不能为空")
    @ApiModelProperty(value = "任务名称")
    private String quartzName;

    @NotEmpty(message = "cron表达式不能为空")
    @ApiModelProperty(value = "cron表达式")
    private String cron;

    @ApiModelProperty(value = "状态")
    private Integer quartzStatus;

    @ApiModelProperty(value = "消息")
    private String msg;

    @NotEmpty(message = "任务处理类不能为空")
    @ApiModelProperty(value = "任务处理类")
    private String quartzClass;

    @NotNull(message = "生效时间不能为空")
    @ApiModelProperty(value = "生效时间")
    private String startTime;

    @NotNull(message = "失效时间不能为空")
    @ApiModelProperty(value = "失效时间")
    private String endTime;

    @Override
    public QuartzConfig toPo(Class<QuartzConfig> clazz) {
        QuartzConfig quartzConfig = new QuartzConfig();
        BeanUtils.copyProperties(this, quartzConfig);
        quartzConfig.setStartTime(DateUtil.parse(this.startTime, "yyyy-MM-dd HH:mm:ss"));
        quartzConfig.setEndTime(DateUtil.parse(this.endTime, "yyyy-MM-dd HH:mm:ss"));
        return quartzConfig;
    }

}
