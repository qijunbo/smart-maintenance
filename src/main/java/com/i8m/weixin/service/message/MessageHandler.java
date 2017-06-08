package com.i8m.weixin.service.message;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class MessageHandler implements ResponseHandler<String> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Gson gson;

	@Override
	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		String stringResp = EntityUtils.toString(response.getEntity());

		if (log.isDebugEnabled()) {

			log.debug("From weixin : " + "[" + response.getStatusLine() + "]" + stringResp);
		}
		return stringResp;
	}

}
