package com.chinaunicom.js.common.core.tools.aop.proxy;


import com.chinaunicom.js.common.core.tools.aop.ProxyUtil;
import com.chinaunicom.js.common.core.tools.aop.aspects.Aspect;
import com.chinaunicom.js.common.core.tools.aop.interceptor.JdkInterceptor;

/**
 * JDK实现的切面代理
 * 
 * @author looly
 *
 */
public class JdkProxyFactory extends ProxyFactory{

	@Override
	@SuppressWarnings("unchecked")
	public <T> T proxy(T target, Aspect aspect) {
		return (T) ProxyUtil.newProxyInstance(target.getClass().getClassLoader(), new JdkInterceptor(target, aspect), target.getClass().getInterfaces());
	}
}
