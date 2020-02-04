package com.cx.chenxing.articlereply.result;

import com.cx.chenxing.mybatisutils.PageCondition;
import java.util.Date;

public class ArticleReplyBean extends PageCondition {

	private Long id;
	private Long articleId;
	private Long replyUid;
	private String replyTime;
	private String replyComment;
	private Long replyPid;
	private Long breplyUid;
	private String replyUname;
	private String breplyUname;
	private String replyImg;
	private String breplyImg;

	public String getReplyImg() {
		return replyImg;
	}

	public void setReplyImg(String replyImg) {
		this.replyImg = replyImg;
	}

	public String getBreplyImg() {
		return breplyImg;
	}

	public void setBreplyImg(String breplyImg) {
		this.breplyImg = breplyImg;
	}

	public String getReplyUname() {
		return replyUname;
	}

	public void setReplyUname(String replyUname) {
		this.replyUname = replyUname;
	}

	public String getBreplyUname() {
		return breplyUname;
	}

	public void setBreplyUname(String breplyUname) {
		this.breplyUname = breplyUname;
	}

	private String iszan;//是否已赞

	public String getIszan() {
		return iszan;
	}

	public void setIszan(String iszan) {
		this.iszan = iszan;
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
