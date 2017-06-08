package com.i8m.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String weixin;

	private String tag;

	private String telephone;

	private String company;

	private String address;

	private List<Donate> donates;

	private Set<URL> urls;

	public User(String weixin) {
		this.weixin = weixin;
		this.donates = new ArrayList<Donate>();
		this.urls = new HashSet<URL>();
	}

	public String getAddress() {
		return address;
	}

	public String getCompany() {
		return company;
	}

	public List<Donate> getDonates() {
		return donates;
	}

	public String getTag() {
		return tag;
	}

	public String getTelephone() {
		return telephone;
	}

	public Set<URL> getUrls() {
		return urls;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDonates(List<Donate> donates) {
		this.donates = donates;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

}
