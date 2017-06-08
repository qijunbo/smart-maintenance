package com.i8m.weixin.service.message;

import com.i8m.health.web.resource.MsgType;

public class ImageMessage extends Message {

	private Image image= new Image();

	public ImageMessage() {
		super.setMsgtype(MsgType.image.name());
	}

	public Image getImage() {
		return image;
	}

}
