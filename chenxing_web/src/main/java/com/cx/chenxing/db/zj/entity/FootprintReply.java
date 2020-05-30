package com.cx.chenxing.db.zj.entity;


public class FootprintReply {
	private Integer replyId;
	
	private Integer footprintId;
	
	private String replyTime;
	
	private Integer replyUserId;
	
	private String replyUserName;

	private Integer breplyUserId;
	
	private String replyContent;
	
	private String breplyUserName;

	private String picurl;

	private String iszan;//是否已赞
	
	public String getIszan() {
		return iszan;
	}

	public void setIszan(String iszan) {
		this.iszan = iszan;
	}
	
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getBreplyUserName() {
		return breplyUserName;
	}

	public void setBreplyUserName(String breplyUserName) {
		this.breplyUserName = breplyUserName;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	private Integer replyParentId;
	
	public Integer getReplyParentId() {
		return replyParentId;
	}

	public void setReplyParentId(Integer replyParentId) {
		this.replyParentId = replyParentId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getFootprintId() {
		return footprintId;
	}

	public void setFootprintId(Integer footprintId) {
		this.footprintId = footprintId;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public Integer getBreplyUserId() {
		return breplyUserId;
	}

	public void setBreplyUserId(Integer breplyUserId) {
		this.breplyUserId = breplyUserId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", articleId=" + footprintId
				+ ", replyTime=" + replyTime + ", replyUserId=" + replyUserId
				+ ", breplyUserId=" + breplyUserId + ", replyContent="
				+ replyContent + "]";
	}
	
	
}
