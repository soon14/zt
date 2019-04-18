package com.chinaunicom.js.common.web.filter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by GaoHaoYu on 2019/3/26.
 */
@WebFilter(urlPatterns = "/*", filterName = "requestId")
public class RequestFilter extends com.chinaunicom.log.filter.RequestFilter {
}
