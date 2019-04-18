package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;

import com.chinaunicom.js.common.core.tools.core.util.StrUtil;
import com.chinaunicom.js.common.core.tools.extra.tokenizer.TokenizerEngine;
import com.chinaunicom.js.common.core.tools.extra.tokenizer.Result;

/**
 * HanLP分词引擎实现<br>
 * 项目地址：https://github.com/hankcs/HanLP
 * 
 * @author looly
 *
 */
public class HanLPEngine implements TokenizerEngine {

	private Segment seg;
	
	/**
	 * 构造
	 * 
	 */
	public HanLPEngine() {
		this(HanLP.newSegment());
	}
	
	/**
	 * 构造
	 * 
	 * @param seg {@link Segment}
	 */
	public HanLPEngine(Segment seg) {
		this.seg = seg;
	}

	@Override
	public Result parse(CharSequence text) {
		return new HanLPResult(this.seg.seg(StrUtil.str(text)));
	}

}
