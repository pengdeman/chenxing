/*
 * Powered By [meinong]
 * Web Site: http://www.meinong.cn
 * Since 2015 - 2018
 */

package com.cx.chenxing.articlereply.result;

import com.cx.chenxing.mybatisutils.PageCondition;
import java.util.Date;

public class ArticleReplyBean extends PageCondition {

	private Long id;
	private Long articleId;
	private Long replyUid;
	private Date replyTime;
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
		public void setReplyTime(Date value) {
			this.replyTime = value;
		}	
		public Date getReplyTime() {
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