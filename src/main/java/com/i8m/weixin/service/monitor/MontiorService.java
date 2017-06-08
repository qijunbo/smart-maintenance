package com.i8m.weixin.service.monitor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i8m.AppContext;
import com.i8m.common.http.HttpGetClient;
import com.i8m.repository.URL;
import com.i8m.repository.User;
import com.i8m.weixin.service.message.MessageFactory;
import com.i8m.weixin.service.message.MessageService;
import com.i8m.weixin.service.print.PrintService;

@Service
public class MontiorService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpGetClient client;

	@Autowired
	private MonitorHandler handler;

	@Autowired
	private MessageService msgService;

	@Autowired
	private PrintService printService;

	@Autowired
	private MessageFactory factory;

	@Autowired
	private AppContext appContext;

	public MonitorResult getVMStatus(URL url) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Diagnosing URL " + url);
		}

		MonitorResult result = client.execute(url.getUrl().trim(), handler);
		result.setService(url.getService());
		return result;
	}

	public void getVMstatusAndSendtoUser(User user) {

		List<MonitorResult> result = getVMStatusOfUser(user);
		msgService.sendAnyThing(user.getWeixin(), printService.printServiceStatusOfUser(user.getWeixin(), result));

	}

	public void createVMStatusLink(User user) {
		String url = appContext.get(AppContext.DomainName) + "/vm/status/" + user.getWeixin();
		url = url.replace("http://", "");
		msgService.sendAnyThing(user.getWeixin(), url);
	}

	public List<MonitorResult> getVMStatusOfUser(User user) {

		long today = System.currentTimeMillis();
		List<MonitorResult> result = new ArrayList<MonitorResult>();

		if (user == null) {
			return result;
		}

		for (URL url : user.getUrls()) {
			try {
				// if today is less than expired date.
				if (today < url.getExpiredate()) {
					result.add(getVMStatus(url));
				} else {

					// 建议捐款
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.debug(e.getMessage());
			}
		}

		return result;
	}

}
