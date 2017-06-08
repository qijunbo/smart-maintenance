package com.i8m.weixin.service.tag;

import java.util.ArrayList;
import java.util.List;

public class TagList {

	private String errcode;
	private String errmsg;
	private List<Tag> taglist = new ArrayList<Tag>();

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<Tag> getTaglist() {
		return taglist;
	}

}
