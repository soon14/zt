package com.chinaunicom.js.common.core.tools.extra.tokenizer.engine.word;

import java.util.Iterator;
import java.util.List;

import com.chinaunicom.js.common.core.tools.extra.tokenizer.Result;
import com.chinaunicom.js.common.core.tools.extra.tokenizer.Word;

/**
 * Word分词结果实现<br>
 * 项目地址：https://github.com/ysc/word
 * 
 * @author looly
 *
 */
public class WordResult implements Result{

	private Iterator<org.apdplat.word.segmentation.Word> wordIter;

	/**
	 * 构造
	 * 
	 * @param result 分词结果
	 */
	public WordResult(List<org.apdplat.word.segmentation.Word> result) {
		this.wordIter = result.iterator();
	}

	@Override
	public boolean hasNext() {
		return this.wordIter.hasNext();
	}

	@Override
	public Word next() {
		return new WordWord(this.wordIter.next());
	}

	@Override
	public void remove() {
		this.wordIter.remove();
	}

	@Override
	public Iterator<Word> iterator() {
		return this;
	}
	
}
