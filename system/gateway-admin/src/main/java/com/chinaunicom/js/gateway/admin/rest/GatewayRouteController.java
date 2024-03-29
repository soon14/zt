package com.chinaunicom.js.gateway.admin.rest;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteQueryForm;
import com.chinaunicom.js.gateway.admin.entity.ov.GatewayRouteVo;
import com.chinaunicom.js.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.chinaunicom.js.gateway.admin.service.IGatewayRouteService;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteForm;
import com.chinaunicom.js.gateway.admin.entity.po.GatewayRoute;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
public class GatewayRouteController {

    @Autowired
    private IGatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        return gatewayRoutService.add(gatewayRoutForm);
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable long id) {
        return gatewayRoutService.delete(id);
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable long id, @Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        return gatewayRoutService.update(id, gatewayRoutForm);
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable long id) {
        return gatewayRoutService.get(id);
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result get(@RequestParam String uri) {
        return gatewayRoutService.query(uri);
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRoutQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        return gatewayRoutService.queryByConditions(gatewayRouteQueryForm);
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/overload")
    public Result overload() {
        return gatewayRoutService.overload();
    }

}