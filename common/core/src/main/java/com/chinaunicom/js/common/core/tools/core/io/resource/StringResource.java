package com.chinaunicom.js.common.core.tools.core.io.resource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;

import com.chinaunicom.js.common.core.tools.core.io.IORuntimeException;
import com.chinaunicom.js.common.core.tools.core.io.IoUtil;
import com.chinaunicom.js.common.core.tools.core.util.CharsetUtil;

/**
 * 字符串资源，字符串做为资源
 * 
 * @author looly
 * @since 4.1.0
 */
public class StringResource implements Resource {

	private String data;
	private String name;
	private Charset charset;

	/**
	 * 构造，使用UTF8编码
	 *
	 * @param data 资源数据
	 */
	public StringResource(String data) {
		this(data, null);
	}

	/**
	 * 构造，使用UTF8编码
	 *
	 * @param data 资源数据
	 * @param name 资源名称
	 */
	public StringResource(String data, String name) {
		this(data, name, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * 构造
	 *
	 * @param data 资源数据
	 * @param name 资源名称
	 * @param charset 编码
	 */
	public StringResource(String data, String name, Charset charset) {
		this.data = data;
		this.name = name;
		this.charset = charset;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public URL getUrl() {
		return null;
	}

	@Override
	public InputStream getStream() {
		return new ByteArrayInputStream(readBytes());
	}

	@Override
	public BufferedReader getReader(Charset charset) {
		return IoUtil.getReader(new StringReader(this.data));
	}

	@Override
	public String readStr(Charset charset) throws IORuntimeException {
		return this.data;
	}

	@Override
	public String readUtf8Str() throws IORuntimeException {
		return this.data;
	}

	@Override
	public byte[] readBytes() throws IORuntimeException {
		return this.data.getBytes(this.charset);
	}

}
