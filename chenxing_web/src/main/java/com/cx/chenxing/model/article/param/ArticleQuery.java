package com.cx.chenxing.model.article.param;

import com.cx.chenxing.utils.mybatisutils.PageCondition;

public class ArticleQuery extends PageCondition {
	private Long id;
	private String article;
	private String creTime;
	private Long creUid;
	private String picurl;
	private String location;
	private String lng;
	private String lat;
	private Integer zfnum;
	private Integer plnum;
	private Integer ydnum;
	private Integer dznum;
	private String shows;
	//user表
	private String userName;
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setShows(String value) {
		this.shows = value;
	}
	public String getShows() {
		return this.shows;
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
		public void setCreTime(String value) {
			this.creTime = value;
		}	
		public String getCreTime() {
			return this.creTime;
		}
		public void setCreUid(Long value) {
			this.creUid = value;
		}	
		public Long getCreUid() {
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
