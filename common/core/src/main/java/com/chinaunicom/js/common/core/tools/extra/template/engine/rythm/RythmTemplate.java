package com.chinaunicom.js.common.core.tools.extra.template.engine.rythm;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

import com.chinaunicom.js.common.core.tools.core.convert.Convert;
import com.chinaunicom.js.common.core.tools.core.lang.TypeReference;
import com.chinaunicom.js.common.core.tools.extra.template.AbstractTemplate;
import com.chinaunicom.js.common.core.tools.extra.template.engine.beetl.BeetlTemplate;

/**
 * Rythm模板包装
 * 
 * @author looly
 *
 */
public class RythmTemplate extends AbstractTemplate implements Serializable {
	private static final long serialVersionUID = -132774960373894911L;

	private org.rythmengine.template.ITemplate rawTemplate;
	
	/**
	 * 包装Rythm模板
	 * 
	 * @param template Rythm的模板对象 {@link org.rythmengine.template.ITemplate}
	 * @return {@link BeetlTemplate}
	 */
	public static RythmTemplate wrap(org.rythmengine.template.ITemplate template) {
		return (null == template) ? null : new RythmTemplate(template);
	}
	
	/**
	 * 构造
	 * 
	 * @param rawTemplate Velocity模板对象
	 */
	public RythmTemplate(org.rythmengine.template.ITemplate rawTemplate) {
		this.rawTemplate = rawTemplate;
	}

	@Override
	public void render(Map<?, ?> bindingMap, Writer writer) {
		final Map<String, Object> map = Convert.convert(new TypeReference<Map<String, Object>>() {}, bindingMap);
		rawTemplate.__setRenderArgs(map);
		rawTemplate.render(writer);
	}

	@Override
	public void render(Map<?, ?> bindingMap, OutputStream out) {
		rawTemplate.__setRenderArgs(bindingMap);
		rawTemplate.render(out);
	}
}
