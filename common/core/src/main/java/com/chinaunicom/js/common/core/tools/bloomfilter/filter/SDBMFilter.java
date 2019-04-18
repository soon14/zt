package com.chinaunicom.js.common.core.tools.bloomfilter.filter;

import com.chinaunicom.js.common.core.tools.core.util.HashUtil;

public class SDBMFilter extends AbstractFilter {

	public SDBMFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public SDBMFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashUtil.sdbmHash(str) % size;
	}

}
