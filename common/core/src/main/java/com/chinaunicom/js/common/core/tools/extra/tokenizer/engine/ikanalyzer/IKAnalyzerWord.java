package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.ikanalyzer;

import org.wltea.analyzer.core.Lexeme;

import com.chinaunicom.js.common.core.tools.extra.tokenizer.Word;

/**
 * IKAnalyzer分词中的一个单词包装
 * 
 * @author looly
 *
 */
public class IKAnalyzerWord implements Word {
	
	private Lexeme word;

	/**
	 * 构造
	 * 
	 * @param word {@link Lexeme}
	 */
	public IKAnalyzerWord(Lexeme word) {
		this.word = word;
	}

	@Override
	public String getText() {
		return word.getLexemeText();
	}
	
	@Override
	public int getStartOffset() {
		return word.getBeginPosition();
	}
	
	@Override
	public int getEndOffset() {
		return word.getEndPosition();
	}

	@Override
	public String toString() {
		return getText();
	}
}
