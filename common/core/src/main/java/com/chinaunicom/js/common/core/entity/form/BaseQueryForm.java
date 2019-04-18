package com.chinaunicom.js.common.core.entity.form;

import com.chinaunicom.js.common.core.entity.param.BaseParam;
import com.chinaunicom.log.LoggerHelp;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@ApiModel
@Data
public class BaseQueryForm<P extends BaseParam> extends BaseForm {

    private Integer pageNum;

    private Integer pageSize;

    /**
     * Form转化为Param
     *
     * @param clazz
     * @return
     */
    public P toParam(Class<P> clazz) {
        P p = null;
        try {
            p = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LoggerHelp.error("Param NewInstance Error");
        }
        BeanUtils.copyProperties(this, p);
        return p;
    }

}
