package com.i8m.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "wexin")
public class WexinConfig {

	private String token;

	private String encodingAESKey;

	private String corpID;

	private String secret;

	private WeixinServiceURL service;

	public WeixinServiceURL getService() {
		return service;
	}

	public void setService(WeixinServiceURL service) {
		this.service = service;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public void setEncodingAESKey(String encodingAESKey) {
		this.encodingAESKey = encodingAESKey;
	}

	public String getCorpID() {
		return corpID;
	}

	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

 

}
