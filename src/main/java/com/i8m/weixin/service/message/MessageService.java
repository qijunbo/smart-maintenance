package com.i8m.weixin.service.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.i8m.common.http.HttpPostClient;
import com.i8m.configure.WexinConfig;
import com.i8m.weixin.service.event.MsgEvent;
import com.i8m.weixin.service.token.AccessTokenService;

@Service
public class MessageService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${weixin.message.send.url}")
	private String sendMessageURL;

	@Autowired
	private HttpPostClient client;

	@Autowired
	private WexinConfig configure;

	@Autowired
	private AccessTokenService service;

	@Autowired
	private MessageFactory factory;

	@Autowired
	private MessageHandler handler;

	private void sendMessage(Message message) {
		String uri = String.format(sendMessageURL, service.getAccessToken());
		try {
			client.execute(uri, message, handler);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public void sendFailed(MsgEvent event) {
		sendAnyThing(event.getFromUserName(), "Failed!");
	}

	public void sendHelp(MsgEvent event) {

		sendAnyThing(event.getFromUserName(), configure.getService().getMediaUpload());
	}

	public void sendOK(MsgEvent event) {
		sendAnyThing(event.getFromUserName(), "Done!");
	}

	public void sendAnyThing(String weixin, String msg) {
		Message message = factory.createTextMessage(weixin, msg);
		sendMessage(message);
	}

	public void sendDonate(MsgEvent event) {
		int a = (int) (Math.random() * 100);

		Message message = factory.createImageMessage(event.getFromUserName(),
				a > 50 ? "2QEGHOEAsMx-UhgFgA8R0fEqPbrR08fABK2A3vQUuNn0QRgzI9xgS4b2OmzOM2qCE_BEOGwUun9xp_p5LlvtVig"
						: "2DBcSybHNJaIgDY51KDP1yJCTEfmK7nabUVI5GzfyFbDNd3C2HQ8DqXbHBdQJNL0CpCkOpuZhffP9IODl3_vIeA");
		sendMessage(message);
	}

	public void sendMessageToGroup(String tag, String msg) {
		Message message = factory.createTextGroupMessage(tag, msg);
		sendMessage(message);
	}
}
