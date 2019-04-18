package com.chinaunicom.js.gateway.admin.service;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteForm;
import com.chinaunicom.js.gateway.admin.entity.form.GatewayRouteQueryForm;

public interface IGatewayRouteService {
    /**
     * 获取网关路由
     *
     * @param id
     * @return
     */
    Result get(long id);

    /**
     * 新增网关路由
     *
     * @param gatewayRoutForm
     * @return
     */
    Result add(GatewayRouteForm gatewayRoutForm);

//    /**
//     * 查询网关路由
//     *
//     * @return
//     */
//    List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    /**
     * 查询网关路由
     *
     * @return
     */
    Result queryByConditions(GatewayRouteQueryForm gatewayRouteQueryForm);

    /**
     * 根据uri查询网关路由
     *
     * @return
     */
    Result query(String uri);

    /**
     * 更新网关路由信息
     *
     * @param gatewayRoutForm
     */
    Result update(long id, GatewayRouteForm gatewayRoutForm);

    /**
     * 根据id删除网关路由
     *
     * @param id
     */
    Result delete(long id);

    /**
     * 重新加载网关路由配置到redis
     * @return 成功返回true
     */
    Result overload();
}
