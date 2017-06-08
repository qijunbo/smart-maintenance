package com.i8m.repository;

import com.google.gson.Gson;

/**
 * 
 * @author qijunbo
 * 
 *         All requests to HiPay TPP REST API require the Merchant to
 *         authenticate himself using the HTTP Basic Authentication.
 * 
 *         Authorization Basic base64("<API login>:<API password>")
 * 
 * @see com.ibm.payment.http.hipay.com.ibm.payment.http.hipay.HipayCashinHttpClient
 *
 */

public class URL {
	private String url;
	private String service;
	private String description;
	private long expiredate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(long expiredate) {
		this.expiredate = expiredate;
	}
	

	@Override
	public int hashCode() {
		return this.service.hashCode();
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
