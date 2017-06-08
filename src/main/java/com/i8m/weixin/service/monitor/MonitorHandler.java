package com.i8m.weixin.service.monitor;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MonitorHandler implements ResponseHandler<MonitorResult> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public MonitorResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		String stringResp = EntityUtils.toString(response.getEntity());
		final StatusLine statusLine = response.getStatusLine();

		if (log.isDebugEnabled()) {
			log.debug("Status [" + response.getStatusLine() + "]");
		}
		MonitorResult result = new MonitorResult();
		if (statusLine.getStatusCode() >= 300) {
			result.setContent(null);
			result.setStatus(MonitorResult.Status.Failed);

		} else {
			result.setContent(stringResp.length() > 64 ? stringResp.substring(0, 61) + "..." : stringResp);
			result.setStatus(MonitorResult.Status.OK);

		}

		return result;
	}

}
