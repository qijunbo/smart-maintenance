package com.i8m.common.http;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

@Configuration
public class HttpPostClient {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected Gson gson;

	public <T> T execute(String uri, HttpEntity entity, ResponseHandler<T> handler) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending Http Post to: " + uri);
		}
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(entity);
		T response = httpClient.execute(httpPost, handler);
		httpClient.close();
		return response;

	}

	public <T> T execute(String uri, Object request, ResponseHandler<T> handler) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending Http Post to: " + uri);
		}

		HttpPost httpPost = new HttpPost(uri);
		httpPost.setHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

		if (request != null) {
			HttpEntity entity = createRequestHttpEntity(request);
			httpPost.setEntity(entity);
		}

		T response = httpClient.execute(httpPost, handler);

		httpClient.close();
		return response;

	}

	private HttpEntity createRequestHttpEntity(Object pojo) throws Exception {

		String json = gson.toJson(pojo);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Request Data: " + json);
		}
		StringEntity input = new StringEntity(json, Charsets.UTF_8);

		input.setContentType(MediaType.APPLICATION_JSON_VALUE);
		input.setContentEncoding("UTF-8");
		return input;

	}

}
