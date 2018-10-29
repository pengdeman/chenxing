package com.cx.chenxing.article.entity;

import java.util.Date;

public class Article implements java.io.Serializable{
	private static final long serialVersionUID = -1L;

	private Long id;
	private String article;
	private Date creTime;
	private Integer creUid;
	private String picurl;
	private String location;
	private String lng;
	private String lat;
	private Integer zfnum;
	private Integer plnum;
	private Integer ydnum;
	private Integer dznum;
	private String show;

	public void setShow(String value) {
		this.show = value;
	}
	public String getShow() {
		return this.show;
	}
		public void setId(Long value) {
			this.id = value;
		}	
		public Long getId() {
			return this.id;
		}
		public void setArticle(String value) {
			this.article = value;
		}	
		public String getArticle() {
			return this.article;
		}
		public void setCreTime(Date value) {
			this.creTime = value;
		}	
		public Date getCreTime() {
			return this.creTime;
		}
		public void setCreUid(Integer value) {
			this.creUid = value;
		}	
		public Integer getCreUid() {
			return this.creUid;
		}
		public void setPicurl(String value) {
			this.picurl = value;
		}	
		public String getPicurl() {
			return this.picurl;
		}
		public void setLocation(String value) {
			this.location = value;
		}	
		public String getLocation() {
			return this.location;
		}
		public void setLng(String value) {
			this.lng = value;
		}	
		public String getLng() {
			return this.lng;
		}
		public void setLat(String value) {
			this.lat = value;
		}	
		public String getLat() {
			return this.lat;
		}
		public void setZfnum(Integer value) {
			this.zfnum = value;
		}	
		public Integer getZfnum() {
			return this.zfnum;
		}
		public void setPlnum(Integer value) {
			this.plnum = value;
		}	
		public Integer getPlnum() {
			return this.plnum;
		}
		public void setYdnum(Integer value) {
			this.ydnum = value;
		}	
		public Integer getYdnum() {
			return this.ydnum;
		}
		public void setDznum(Integer value) {
			this.dznum = value;
		}	
		public Integer getDznum() {
			return this.dznum;
		}
}
