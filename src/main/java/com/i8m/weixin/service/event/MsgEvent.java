package com.i8m.weixin.service.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class MsgEvent {

	@XStreamAlias("ToUserName")
	private String toUserName;

	@XStreamAlias("FromUserName")
	private String fromUserName;
	@XStreamAlias("CreateTime")
	private String createTime;

	@XStreamAlias("MsgType")
	private String msgType;

	@XStreamAlias("Content")
	private String content;

	@XStreamAlias("PicUrl")
	private String picUrl;

	@XStreamAlias("MsgId")
	private String msgId;

	@XStreamAlias("MediaId")
	private String mediaId;

	@XStreamAlias("Encrypt")
	private String encrypt;

	@XStreamAlias("AgentID")
	private String agentID;

	@XStreamAlias("Event")
	private String event;

	@XStreamAlias("EventKey")
	private String eventKey;

	@XStreamAlias("ScanCodeInfo")
	private ScanCodeInfo scanCodeInfo;

	public String getAgentID() {
		return agentID;
	}

	public String getContent() {
		return content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public String getEvent() {
		return event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getMsgId() {
		return msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return scanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

}
