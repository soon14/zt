package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.ikanalyzer;

import java.io.IOException;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.chinaunicom.js.common.core.tools.extra.tokenizer.AbstractResult;
import com.chinaunicom.js.common.core.tools.extra.tokenizer.TokenizerException;
import com.chinaunicom.js.common.core.tools.extra.tokenizer.Word;

/**
 * IKAnalyzer分词结果实现<br>
 * 项目地址：https://github.com/yozhao/IKAnalyzer
 * 
 * @author looly
 *
 */
public class IKAnalyzerResult extends AbstractResult {

	private IKSegmenter seg;

	/**
	 * 构造
	 * 
	 * @param seg 分词结果
	 */
	public IKAnalyzerResult(IKSegmenter seg) {
		this.seg = seg;
	}

	@Override
	protected Word nextWord() {
		Lexeme next = null;
		try {
			next = this.seg.next();
		} catch (IOException e) {
			throw new TokenizerException(e);
		}
		if (null != next) {
			return new IKAnalyzerWord(next);
		}
		return null;
	}
}
