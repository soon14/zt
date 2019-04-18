package com.chinaunicom.js.common.core.tools.aop.proxy;


import com.chinaunicom.js.common.core.tools.aop.aspects.Aspect;
import com.chinaunicom.js.common.core.tools.aop.interceptor.CglibInterceptor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * 基于Cglib的切面代理工厂
 * 
 * @author looly
 *
 */
public class CglibProxyFactory extends ProxyFactory{

	@Override
	@SuppressWarnings("unchecked")
	public <T> T proxy(T target, Aspect aspect) {
		final Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new CglibInterceptor(target, aspect));
		return (T) enhancer.create();
	}

}
