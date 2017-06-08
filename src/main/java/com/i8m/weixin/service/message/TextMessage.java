package com.i8m.weixin.service.message;

import com.i8m.health.web.resource.MsgType;

public class TextMessage extends Message {
	private Text text = new Text();

	public TextMessage() {
		super.setMsgtype(MsgType.text.name());
	}

	public Text getText() {
		return text;
	}

}
