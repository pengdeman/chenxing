package com.cx.chenxing.db.zj.entity;

public class Wz {
	private Integer id;
	
	private String creTime;
	
	private String adr;
	
	private String lng;
	
	private String lat;
	
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}
}
