package com.chinaunicom.js.gateway.admin.entity.ov;

import com.chinaunicom.js.common.core.entity.vo.BaseVo;
import com.chinaunicom.js.gateway.admin.entity.po.FilterDefinition;
import com.chinaunicom.js.gateway.admin.entity.po.GatewayRoute;
import com.chinaunicom.js.gateway.admin.entity.po.PredicateDefinition;
import com.chinaunicom.log.LoggerHelp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GatewayRouteVo extends BaseVo {
    private Long id;
    private String uri;
    private Integer order;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();

    public GatewayRouteVo(GatewayRoute gatewayRoute) {
        this.id = gatewayRoute.getId();
        this.uri = gatewayRoute.getUri();
        this.order = gatewayRoute.getOrders();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.filters = objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            });
            this.predicates = objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            });
        } catch (IOException e) {
            LoggerHelp.error("网关路由对象转换失败", e);
        }
    }
}
