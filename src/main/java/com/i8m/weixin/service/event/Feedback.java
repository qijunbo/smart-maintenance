package com.i8m.weixin.service.event;

public class Feedback {
	
	private String encrypt;
	private String msgSignature;
	private String timeStamp;
	private String nonce;
	
	
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	public String getMsgSignature() {
		return msgSignature;
	}
	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	

}
