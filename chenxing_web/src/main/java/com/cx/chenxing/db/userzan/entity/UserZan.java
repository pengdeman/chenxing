package com.cx.chenxing.db.userzan.entity;

import java.util.Date;

public class UserZan implements java.io.Serializable{
	private static final long serialVersionUID = -1L;

	private Long id;
	private Long zanUid;
	private Date zanTime;
	private Long zanArticleId;
	private Long zanArticleRid;
	private Long bzanUid;

		public void setId(Long value) {
			this.id = value;
		}	
		public Long getId() {
			return this.id;
		}
		public void setZanUid(Long value) {
			this.zanUid = value;
		}	
		public Long getZanUid() {
			return this.zanUid;
		}
		public void setZanTime(Date value) {
			this.zanTime = value;
		}	
		public Date getZanTime() {
			return this.zanTime;
		}
		public void setZanArticleId(Long value) {
			this.zanArticleId = value;
		}	
		public Long getZanArticleId() {
			return this.zanArticleId;
		}
		public void setZanArticleRid(Long value) {
			this.zanArticleRid = value;
		}	
		public Long getZanArticleRid() {
			return this.zanArticleRid;
		}
		public void setBzanUid(Long value) {
			this.bzanUid = value;
		}	
		public Long getBzanUid() {
			return this.bzanUid;
		}
}
