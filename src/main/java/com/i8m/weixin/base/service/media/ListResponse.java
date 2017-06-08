package com.i8m.weixin.base.service.media;

import java.util.List;

public class ListResponse {

	private String errcode;
	private String errmsg;
	private String type;
	private String total_count;
	private String item_count;
	private List<Item> itemlist;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public String getItem_count() {
		return item_count;
	}

	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}

	public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

}
