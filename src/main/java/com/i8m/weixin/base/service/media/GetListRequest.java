package com.i8m.weixin.base.service.media;

public class GetListRequest {
	private String type;
	private int agentid;
	private int offset;
	private int count;

	public GetListRequest(MediaType type, int agentid, int offset, int count) {
		super();
		this.type = type.name();
		this.agentid = agentid;
		this.offset = offset;
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public int getAgentid() {
		return agentid;
	}

	public int getOffset() {
		return offset;
	}

	public int getCount() {
		return count;
	}

}
