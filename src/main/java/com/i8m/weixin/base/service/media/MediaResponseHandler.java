package com.i8m.weixin.base.service.media;

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
public class MediaResponseHandler implements ResponseHandler<MediaResponse> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Gson gson;

	@Override
	public MediaResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		String stringResp = EntityUtils.toString(response.getEntity());

		if (log.isDebugEnabled()) {
			log.debug("From weixin : " + "[" + response.getStatusLine() + "]" + stringResp);
		}

		MediaResponse media = gson.fromJson(stringResp, MediaResponse.class);
		return media;
	}

}
