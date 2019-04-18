package com.chinaunicom.js.gateway.admin.service.impl;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.gateway.admin.dao.GatewayRouteMapper;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteForm;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteQueryForm;
import com.chinaunicom.js.gateway.admin.entity.ov.GatewayRouteVo;
import com.chinaunicom.js.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.chinaunicom.js.gateway.admin.entity.po.GatewayRoute;
import com.chinaunicom.js.gateway.admin.service.IGatewayRouteService;
import com.chinaunicom.log.LoggerHelp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GatewayRouteService implements IGatewayRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result add(GatewayRouteForm gatewayRoutForm) {
        LoggerHelp.info("name:", gatewayRoutForm);
        GatewayRoute gatewayRoute = gatewayRoutForm.toPo(GatewayRoute.class);
        long gatewayId = gatewayRouteMapper.insert(gatewayRoute);
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVo(gatewayRoute)));
        return Result.success(gatewayId);
    }

    @Override
    public Result queryByConditions(GatewayRouteQueryForm gatewayRouteQueryForm) {
        Page page = PageHelper.startPage(gatewayRouteQueryForm.getPageNum(), gatewayRouteQueryForm.getPageSize());
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class));
        List<GatewayRouteVo> gatewayRoutesVo = gatewayRoutes.stream().map(GatewayRouteVo::new).collect(Collectors.toList());
        PageInfo<GatewayRouteVo> pageInfo = new PageInfo<>(gatewayRoutesVo);
        pageInfo.setTotal(page.getTotal());
        LoggerHelp.info("pageInfo:", pageInfo);
        return Result.success(pageInfo);
    }

    @Override
    public Result delete(long id) {
        gatewayRouteMapper.delete(id);
        stringRedisTemplate.delete(GATEWAY_ROUTES + id);
        return Result.success();
    }

    @Override
    public Result update(long id, GatewayRouteForm gatewayRoutForm) {
        GatewayRoute gatewayRoute = gatewayRoutForm.toPo(GatewayRoute.class);
        gatewayRoute.setId(id);
        gatewayRouteMapper.update(gatewayRoute);
        stringRedisTemplate.delete(GATEWAY_ROUTES + gatewayRoute.getId());
        stringRedisTemplate.opsForValue().set(GATEWAY_ROUTES, toJson(new GatewayRouteVo(gatewayRouteMapper.select((gatewayRoute.getId())))));
        return Result.success();
    }

    @Override
    public Result get(long id) {
        LoggerHelp.info("get with id:{}", id);
        GatewayRoute gatewayRoute = gatewayRouteMapper.select(id);
        return Result.success(new GatewayRouteVo(gatewayRoute));
    }

    @Override
    public Result query(String uri) {
        List<GatewayRouteVo> gatewayRoutesVo = gatewayRouteMapper.query(new GatewayRouteQueryParam(uri)).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
        return Result.success(gatewayRoutesVo.stream().findFirst());
    }

    @Override
    public Result overload() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteMapper.query(new GatewayRouteQueryParam());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        gatewayRoutes.forEach(gatewayRoute ->
                opsForValue.set(GATEWAY_ROUTES + gatewayRoute.getId(), toJson(new GatewayRouteVo(gatewayRoute)))
        );
        return Result.success(true);
    }

    /**
     * GatewayRoute转换为json
     *
     * @param gatewayRouteVo redis需要的vo
     * @return json string
     */
    private String toJson(GatewayRouteVo gatewayRouteVo) {
        String routeDefinitionJson = Strings.EMPTY;
        try {
            routeDefinitionJson = new ObjectMapper().writeValueAsString(gatewayRouteVo);
        } catch (JsonProcessingException e) {
            LoggerHelp.error("网关对象序列化为json String", e);
        }
        return routeDefinitionJson;
    }
}
