package com.chinaunicom.js.common.core.tools.extra.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 用户名密码验证器
 * 
 * @author looly
 * @since 3.1.2
 */
public class UserPassAuthenticator extends Authenticator {

	private String user;
	private String pass;

	/**
	 * 构造
	 * 
	 * @param user 用户名
	 * @param pass 密码
	 */
	public UserPassAuthenticator(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.user, this.pass);
	}

}
