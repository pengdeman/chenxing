package com.cx.chenxing.model.articlereply.param;

import com.cx.chenxing.utils.mybatisutils.PageCondition;

public class ArticleReplyQuery extends PageCondition {
	private Long id;
	private Long articleId;
	private Long replyUid;
	private String replyTime;
	private String replyComment;
	private Long replyPid;
	private Long breplyUid;

	//用户表
	private String userName;
	private String img;
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	private String iszan;//是否已赞

	public String getIszan() {
		return iszan;
	}

	public void setIszan(String iszan) {
		this.iszan = iszan;
	}

	public void setUserName(String value) {
		this.userName = value;
	}
	public String getUserName() {
		return this.userName;
	}
		public void setId(Long value) {
			this.id = value;
		}	
		public Long getId() {
			return this.id;
		}
		public void setArticleId(Long value) {
			this.articleId = value;
		}	
		public Long getArticleId() {
			return this.articleId;
		}
		public void setReplyUid(Long value) {
			this.replyUid = value;
		}	
		public Long getReplyUid() {
			return this.replyUid;
		}
		public void setReplyTime(String value) {
			this.replyTime = value;
		}	
		public String getReplyTime() {
			return this.replyTime;
		}
		public void setReplyComment(String value) {
			this.replyComment = value;
		}	
		public String getReplyComment() {
			return this.replyComment;
		}
		public void setReplyPid(Long value) {
			this.replyPid = value;
		}	
		public Long getReplyPid() {
			return this.replyPid;
		}
		public void setBreplyUid(Long value) {
			this.breplyUid = value;
		}	
		public Long getBreplyUid() {
			return this.breplyUid;
		}
}
