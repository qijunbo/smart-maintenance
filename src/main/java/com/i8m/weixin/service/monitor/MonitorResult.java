package com.i8m.weixin.service.monitor;

public class MonitorResult {

	public static enum Status {
		OK, Failed
	};

	private String service;

	private Status status;

	private String content;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
