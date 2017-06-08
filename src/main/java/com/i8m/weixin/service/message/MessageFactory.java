package com.i8m.weixin.service.message;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageFactory {

	public Message createTextMessage(String toUser, String content) {
		TextMessage message = new TextMessage();
		message.setAgentid("1");
		message.setTouser(toUser);
		message.getText().setContent(content);
		return message;
	}

	public Message createTextGroupMessage(String tag, String content) {
		TextMessage message = new TextMessage();
		message.setAgentid("1");
		message.setTotag(tag);
		message.getText().setContent(content);
		return message;
	}

	public Message createImageMessage(String toUser, String mediaId) {
		ImageMessage message = new ImageMessage();
		message.setAgentid("1");
		message.setTouser(toUser);
		message.getImage().setMedia_id(mediaId);
		return message;
	}

}
