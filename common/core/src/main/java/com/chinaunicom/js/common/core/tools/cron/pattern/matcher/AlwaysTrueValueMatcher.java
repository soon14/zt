package com.chinaunicom.js.common.core.tools.cron.pattern.matcher;

import com.chinaunicom.js.common.core.tools.core.util.StrUtil;

/**
 * 值匹配，始终返回<code>true</code>
 * @author Looly
 *
 */
public class AlwaysTrueValueMatcher implements ValueMatcher{

	@Override
	public boolean match(Integer t) {
		return true;
	}
	
	@Override
	public String toString() {
		return StrUtil.format("[Matcher]: always true.");
	}
}
