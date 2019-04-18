package com.chinaunicom.js.schedule.admin.rest;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigForm;
import com.chinaunicom.js.schedule.admin.entity.form.QuartzConfigQueryForm;
import com.chinaunicom.js.schedule.admin.service.IScheduleRuleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/schedule/rules")
@Api("sechedule rules")
public class ScheduleRuleController {

    @Autowired
    private IScheduleRuleService scheduleRuleService;

    @ApiOperation(value = "新增调度任务", notes = "新增一个调度任务")
    @ApiImplicitParam(name = "quartzConfigForm", value = "新增调度任务form表单", required = true, dataType = "QuartzConfigForm")
    @PostMapping
    public Result add(@Valid @RequestBody QuartzConfigForm quartzConfigForm) {
        return scheduleRuleService.add(quartzConfigForm);
    }

    @ApiOperation(value = "删除调度任务", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "调度任务ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable long id) {
        return scheduleRuleService.delete(id);
    }

    @ApiOperation(value = "修改调度任务", notes = "修改指定调度任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "调度任务ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "quartzConfigForm", value = "调度任务实体", required = true, dataType = "QuartzConfigForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody QuartzConfigForm quartzConfigForm) {
        return scheduleRuleService.update(id, quartzConfigForm);
    }

    @ApiOperation(value = "获取调度任务", notes = "根据id获取指定调度任务信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "调度任务ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable long id) {
        return scheduleRuleService.get(id);
    }

    @ApiOperation(value = "根据name获取调度任务", notes = "根据name获取调度任务信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "调度任务名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result get(@RequestParam String name) {
        return scheduleRuleService.query(name);
    }

    @ApiOperation(value = "搜索调度任务", notes = "根据条件查询调度任务信息")
    @ApiImplicitParam(name = "quartzConfigQueryForm", value = "调度任务查询参数", required = true, dataType = "QuartzConfigQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody QuartzConfigQueryForm quartzConfigQueryForm) {
        return scheduleRuleService.queryByConditions(quartzConfigQueryForm);
    }

    @ApiOperation(value = "重载调度任务", notes = "将所有的调度任务全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/overload")
    public Result overload() {
        return scheduleRuleService.overload();
    }

}