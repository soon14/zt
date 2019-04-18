CREATE TABLE IF NOT EXISTS  `gateway_routes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `route_id` varchar(100) NOT NULL COMMENT '路由id',
  `uri` varchar(100) NOT NULL COMMENT 'uri路径',
  `predicates` text NOT NULL COMMENT '判定器（json）',
  `filters` text COMMENT '过滤器（json）',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
  `status` varchar(1) DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ux_gateway_routes_uri` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='路由表';