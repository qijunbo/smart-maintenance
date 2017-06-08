package com.i8m.weixin.service.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
@Deprecated
public class MsgResponse {

	@XStreamAlias("ToUserName")
	private String toUserName;
	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private long createTime;
	@XStreamAlias("MsgType")
	private String msgType;
	@XStreamAlias("Content")
	private String content;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = add2Cdata(toUserName);
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = add2Cdata(fromUserName);
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = add2Cdata(msgType);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = add2Cdata(content);
	}

	public static String add2Cdata(String value) {
		return "<![CDATA[" + value + "]]>";
	}

}
