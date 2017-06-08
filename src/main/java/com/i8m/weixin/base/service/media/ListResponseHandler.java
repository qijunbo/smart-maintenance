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
import com.i8m.AppContext;

@Service
public class ListResponseHandler implements ResponseHandler<ListResponse> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppContext context;

	@Autowired
	private Gson gson;

	@Override
	public ListResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		String stringResp = EntityUtils.toString(response.getEntity());

		if (log.isDebugEnabled()) {
			log.debug("From weixin : " + "[" + response.getStatusLine() + "]" + stringResp);
		}
		ListResponse media = gson.fromJson(stringResp, ListResponse.class);
		return media;
	}

}
