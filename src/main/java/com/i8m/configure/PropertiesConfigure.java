package com.i8m.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Configuration
public class PropertiesConfigure {

	@Value("${Token}")
	private String token;
	@Value("${EncodingAESKey}")
	private String encodingAESKey;
	@Value("${CorpID}")
	private String corpID;
	@Value("${Secret}")
	private String secret;
	@Value("${weixin.tocken.url}")
	private String accessTokenURL;

	@Value("${error_message}")
	private String error_message;
	@Value("${add_url}")
	private String add_url;

	public String getAccessTokenURL() {
		return accessTokenURL;
	}

	public String getAdd_url() {
		return add_url;
	}

	public String getCorpID() {
		return corpID;
	}

	@Bean
	public WXBizMsgCrypt getCrypt() {

		try {
			return new WXBizMsgCrypt(getToken(), getEncodingAESKey(), getCorpID());
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getEncodingAESKey() {
		return encodingAESKey;
	}

	public String getError_message() {
		return error_message;
	}

	public String getSecret() {
		return secret;
	}

	public String getToken() {
		return token;
	}

}
