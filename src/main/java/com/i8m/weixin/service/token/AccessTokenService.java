package com.i8m.weixin.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.AppContext;

@Service
public class AccessTokenService {

	public static final String AccessToken = "AccessToken";

	@Autowired
	private AppContext context;

	public String getAccessToken() {

		return (String) context.get(AccessToken);
	}

}
