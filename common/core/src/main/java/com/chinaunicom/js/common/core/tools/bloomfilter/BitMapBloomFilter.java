package com.chinaunicom.js.common.core.tools.bloomfilter;

import com.chinaunicom.js.common.core.tools.bloomfilter.filter.DefaultFilter;
import com.chinaunicom.js.common.core.tools.bloomfilter.filter.ELFFilter;
import com.chinaunicom.js.common.core.tools.bloomfilter.filter.JSFilter;
import com.chinaunicom.js.common.core.tools.bloomfilter.filter.PJWFilter;
import com.chinaunicom.js.common.core.tools.bloomfilter.filter.SDBMFilter;

/**
 * BlommFilter 实现 <br>
 * 1.构建hash算法 <br>
 * 2.散列hash映射到数组的bit位置 <br>
 * 3.验证<br>
 * 此实现方式可以指定Hash算法
 * 
 * @author Ansj
 */
public class BitMapBloomFilter implements BloomFilter{

	private BloomFilter[] filters;

	/**
	 * 构造，使用默认的5个过滤器
	 * @param m M值决定BitMap的大小
	 */
	public BitMapBloomFilter(int m) {
		float mNum = m / 5;
		long size = (long) (1L * mNum * 1024 * 1024 * 8);
		
		filters = new BloomFilter[]{
			new DefaultFilter(size),
			new ELFFilter(size),
			new JSFilter(size),
			new PJWFilter(size),
			new SDBMFilter(size)
		};
	}

	/**
	 * 使用自定的多个过滤器建立BloomFilter
	 * 
	 * @param m M值决定BitMap的大小
	 * @param filters Bloom过滤器列表
	 */
	public BitMapBloomFilter(int m, BloomFilter... filters) {
		this.filters = filters;
	}

	/**
	 * 增加字符串到Filter映射中
	 * @param str 字符串
	 */
	@Override
	public boolean add(String str) {
		boolean flag = true;
		for (BloomFilter filter : filters) {
			flag |= filter.add(str);
		}
		return flag;
	}

	/**
	 * 是否可能包含此字符串，此处存在误判
	 * @param str 字符串
	 * @return 是否存在
	 */
	@Override
	public boolean contains(String str) {
		for (BloomFilter filter : filters) {
			if (filter.contains(str) == false) {
				return false;
			}
		}
		return true;
	}
}