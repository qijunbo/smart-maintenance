package com.i8m.weixin.service.token;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.i8m.common.http.HttpGetClient;
import com.i8m.configure.PropertiesConfigure;

@Configuration
@EnableAsync
@EnableScheduling
public class AccessTokenRefreshTask {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpGetClient client;

	@Autowired
	private PropertiesConfigure configure;

	@Autowired
	private AccessTokenHandler handler;

	@Autowired
	private SimpleDateFormat formatter;

	@Scheduled(initialDelay = 6000, fixedRate = 3600000)
	public void refreshAccessToken() {

		try {
			String uri = String.format(configure.getAccessTokenURL(), configure.getCorpID(), configure.getSecret());
			log.debug("Refresh access tocken on " + formatter.format(new Date()));
			log.debug("AccessToken URL: " + uri);

			client.execute(uri, handler);
		} catch (Exception e) {

			e.printStackTrace();

			if (log.isErrorEnabled()) {
				log.error("get AccessToken failed: " + e.getMessage());
			}

		}
	}
}
