package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.word;

import com.chinaunicom.js.common.core.tools.extra.tokenizer.Word;

/**
 * Word分词中的一个单词包装
 * 
 * @author looly
 *
 */
public class WordWord implements Word {
	
	private org.apdplat.word.segmentation.Word word;

	/**
	 * 构造
	 * 
	 * @param word {@link org.apdplat.word.segmentation.Word}
	 */
	public WordWord(org.apdplat.word.segmentation.Word word) {
		this.word = word;
	}

	@Override
	public String getText() {
		return word.getText();
	}
	
	@Override
	public int getStartOffset() {
		return -1;
	}
	
	@Override
	public int getEndOffset() {
		return -1;
	}

	@Override
	public String toString() {
		return getText();
	}
}
