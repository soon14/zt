package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.ansj;

import org.ansj.domain.Term;

import com.chinaunicom.js.common.core.tools.extra.tokenizer.Word;

/**
 * Ansj分词中的一个单词包装
 * 
 * @author looly
 *
 */
public class AnsjWord implements Word {
	private Term term;

	/**
	 * 构造
	 * 
	 * @param term {@link Term}
	 */
	public AnsjWord(Term term) {
		this.term = term;
	}

	@Override
	public String getText() {
		return term.getName();
	}
	
	@Override
	public int getStartOffset() {
		return this.term.getOffe();
	}

	@Override
	public int getEndOffset() {
		return getStartOffset() + getText().length();
	}

	@Override
	public String toString() {
		return getText();
	}
}
