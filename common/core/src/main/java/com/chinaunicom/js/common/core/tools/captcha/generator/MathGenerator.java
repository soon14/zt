package com.chinaunicom.js.common.core.tools.captcha.generator;

import com.chinaunicom.js.common.core.tools.core.util.CharUtil;
import com.chinaunicom.js.common.core.tools.core.util.RandomUtil;
import com.chinaunicom.js.common.core.tools.core.util.StrUtil;

/**
 * 数字计算验证码生成器
 * 
 * @author looly
 * @since 4.1.2
 */
public class MathGenerator implements CodeGenerator {

	private static final String operators = "+-*";

	/** 参与计算数字最大长度 */
	private int numberLength;

	/**
	 * 构造
	 */
	public MathGenerator() {
		this(2);
	}

	/**
	 * 构造
	 * 
	 * @param numberLength 参与计算最大数字位数
	 */
	public MathGenerator(int numberLength) {
		this.numberLength = numberLength;
	}

	@Override
	public String generate() {
		final String code = StrUtil.builder()//
				.append(StrUtil.padAfter(Integer.toString(RandomUtil.randomInt(getLimit())), this.numberLength, CharUtil.SPACE))//
				.append(RandomUtil.randomChar(operators))//
				.append(StrUtil.padAfter(Integer.toString(RandomUtil.randomInt(getLimit())), this.numberLength, CharUtil.SPACE))//
				.toString();
		return code;
	}

	@Override
	public boolean verify(String code, String userInputCode) {
		int result;
		try {
			result = Integer.parseInt(userInputCode);
		} catch (NumberFormatException e) {
			// 用户输入非数字
			return false;
		}

		final int a = Integer.parseInt(StrUtil.sub(code, 0, this.numberLength).trim());
		final char operator = code.charAt(this.numberLength);
		final int b = Integer.parseInt(StrUtil.sub(code, this.numberLength + 1, this.numberLength + 1 + this.numberLength).trim());
		
		switch (operator) {
		case '+':
			return (a + b) == result;
		case '-':
			return (a - b) == result;
		case '*':
			return (a * b) == result;
		default:
			return false;
		}
	}

	@Override
	public int getLength() {
		return this.numberLength * 2 +1;
	}

	/**
	 * 根据长度获取参与计算数字最大
	 * 
	 * @return
	 */
	private int getLimit() {
		return Integer.parseInt("1" + StrUtil.repeat('0', this.numberLength));
	}
}
