package com.chinaunicom.js.common.core.tools.cron.pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinaunicom.js.common.core.tools.core.date.DateUnit;
import com.chinaunicom.js.common.core.tools.core.date.DateUtil;
import com.chinaunicom.js.common.core.tools.core.lang.Assert;

/**
 * 定时任务表达式工具类
 * 
 * @author looly
 *
 */
public class CronPatternUtil {

	/**
	 * 列举指定日期之后（到开始日期对应年年底）内所有匹配表达式的日期
	 * 
	 * @param patternStr 表达式字符串
	 * @param start 起始时间
	 * @param count 列举数量
	 * @param isMatchSecond 是否匹配秒
	 * @return 日期列表
	 */
	public static List<Date> matchedDates(String patternStr, Date start, int count, boolean isMatchSecond) {
		return matchedDates(patternStr, start, DateUtil.endOfYear(start), count, isMatchSecond);
	}

	/**
	 * 列举指定日期范围内所有匹配表达式的日期
	 * 
	 * @param patternStr 表达式字符串
	 * @param start 起始时间
	 * @param end 结束时间
	 * @param count 列举数量
	 * @param isMatchSecond 是否匹配秒
	 * @return 日期列表
	 */
	public static List<Date> matchedDates(String patternStr, Date start, Date end, int count, boolean isMatchSecond) {
		return matchedDates(patternStr, start.getTime(), end.getTime(), count, isMatchSecond);
	}

	/**
	 * 列举指定日期范围内所有匹配表达式的日期
	 * 
	 * @param patternStr 表达式字符串
	 * @param start 起始时间
	 * @param end 结束时间
	 * @param count 列举数量
	 * @param isMatchSecond 是否匹配秒
	 * @return 日期列表
	 */
	public static List<Date> matchedDates(String patternStr, long start, long end, int count, boolean isMatchSecond) {
		return matchedDates(new CronPattern(patternStr), start, end, count, isMatchSecond);
	}

	/**
	 * 列举指定日期范围内所有匹配表达式的日期
	 * 
	 * @param pattern 表达式
	 * @param start 起始时间
	 * @param end 结束时间
	 * @param count 列举数量
	 * @param isMatchSecond 是否匹配秒
	 * @return 日期列表
	 */
	public static List<Date> matchedDates(CronPattern pattern, long start, long end, int count, boolean isMatchSecond) {
		Assert.isTrue(start < end, "Start date is later than end !");

		final List<Date> result = new ArrayList<>(count);
		long step = isMatchSecond ? DateUnit.SECOND.getMillis() : DateUnit.MINUTE.getMillis();
		for (long i = start; i < end; i += step) {
			if (pattern.match(i, isMatchSecond)) {
				result.add(DateUtil.date(i));
				if (result.size() >= count) {
					break;
				}
			}
		}
		return result;
	}
}
