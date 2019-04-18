package com.chinaunicom.js.schedule.admin.entity.param;

import com.chinaunicom.js.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by GaoHaoYu on 2019/4/1.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuartzConfigQueryParam extends BaseParam {
    private String quartzName;
}
