package com.cx.chenxing.articlereply.entity;

import java.util.Date;

public class ArticleReply implements java.io.Serializable{
	private static final long serialVersionUID = -1L;

	private Long id;
	private Long articleId;
	private Long replyUid;
	private String replyTime;
	private String replyComment;
	private Long replyPid;
	private Long breplyUid;

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
