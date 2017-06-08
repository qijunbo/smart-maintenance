package com.i8m.common.http;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

@Configuration
public class HttpGetClient {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected Gson gson;

	public <T> T execute(String uri, ResponseHandler<T> handler) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Send Http Get to: " + uri);
		}

		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpGet.setHeader("charset", "UTF-8");

		T response = httpClient.execute(httpGet, handler);

		httpClient.close();
		return response;

	}

}
