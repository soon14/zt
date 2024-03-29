package com.chinaunicom.js.common.core.tools.extra.template.engine;

import com.jfinal.template.Engine;

import com.chinaunicom.js.common.core.tools.core.util.StrUtil;
import com.chinaunicom.js.common.core.tools.extra.template.TemplateConfig;
import com.chinaunicom.js.common.core.tools.extra.template.TemplateEngine;
import com.chinaunicom.js.common.core.tools.extra.template.TemplateException;
import com.chinaunicom.js.common.core.tools.extra.template.engine.beetl.BeetlEngine;
import com.chinaunicom.js.common.core.tools.extra.template.engine.enjoy.EnjoyEngine;
import com.chinaunicom.js.common.core.tools.extra.template.engine.freemarker.FreemarkerEngine;
import com.chinaunicom.js.common.core.tools.extra.template.engine.rythm.RythmEngine;
import com.chinaunicom.js.common.core.tools.extra.template.engine.thymeleaf.ThymeleafEngine;
import com.chinaunicom.js.common.core.tools.extra.template.engine.velocity.VelocityEngine;
import com.chinaunicom.js.common.core.tools.log.StaticLog;

/**
 * 简单模板工厂，用于根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
 * 
 * @author looly
 *
 */
public class TemplateFactory {
	/**
	 * 根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
	 * 
	 * @param config 模板配置，包括编码、模板文件path等信息
	 * @return {@link Engine}
	 */
	public static TemplateEngine create(TemplateConfig config) {
		final TemplateEngine engine = doCreate(config);
		StaticLog.debug("Use [{}] Engine As Default.", StrUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的模板引擎jar，自动创建对应的模板引擎对象
	 * 
	 * @param config 模板配置，包括编码、模板文件path等信息
	 * @return {@link Engine}
	 */
	private static TemplateEngine doCreate(TemplateConfig config) {
		try {
			return new BeetlEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new FreemarkerEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new VelocityEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new RythmEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new EnjoyEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		try {
			return new ThymeleafEngine(config);
		} catch (NoClassDefFoundError e) {
			// ignore
		}
		throw new TemplateException("No template found ! Please add one of template jar to your project !");
	}
}
