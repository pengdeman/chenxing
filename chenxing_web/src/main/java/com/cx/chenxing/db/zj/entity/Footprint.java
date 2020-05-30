package com.cx.chenxing.db.zj.entity;

public class Footprint extends BaseBean {
	private Integer id;
	
	private Integer userId;//关联用户id
	
	private String creTime;//创建时间
	
	private String location;//地点名称
	
	private String lng;//经度
	
	private String lat;//纬度
	
	private String creName;//创建人
	
	private String picurl;//图片地址
	
	private String pictruename;//图片真实名称
	
	private String comment;//文字内容
	
	private Integer readNum;//阅读量
	
	private Integer clickNum;//点击量
	
	private Integer sayNum;//评论量
	
	private String upicurl;//用户头像
	
	private Integer zfNum;//转发量
	
	private String iszan;//是否已赞
	
	public String getIszan() {
		return iszan;
	}

	public void setIszan(String iszan) {
		this.iszan = iszan;
	}

	public Integer getZfNum() {
		return zfNum;
	}

	public void setZfNum(Integer zfNum) {
		this.zfNum = zfNum;
	}

	public String getUpicurl() {
		return upicurl;
	}

	public void setUpicurl(String upicurl) {
		this.upicurl = upicurl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getCreName() {
		return creName;
	}

	public void setCreName(String creName) {
		this.creName = creName;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getPictruename() {
		return pictruename;
	}

	public void setPictruename(String pictruename) {
		this.pictruename = pictruename;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getSayNum() {
		return sayNum;
	}

	public void setSayNum(Integer sayNum) {
		this.sayNum = sayNum;
	}
}
