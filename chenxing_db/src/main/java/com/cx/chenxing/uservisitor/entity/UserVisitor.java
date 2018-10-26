/*
 * Powered By [meinong]
 * Web Site: http://www.meinong.cn
 * Since 2015 - 2018
 */

package com.cx.chenxing.uservisitor.entity;

import java.util.Date;

/**
 * Created by zhangpeng on 2016/1/12.
 */
public class UserVisitor implements java.io.Serializable{
	private static final long serialVersionUID = -1L;

	private Long id;
	private Long visitorUid;
	private Long bvisitorUid;
	private Date visitorTime;

		public void setId(Long value) {
			this.id = value;
		}	
		public Long getId() {
			return this.id;
		}
		public void setVisitorUid(Long value) {
			this.visitorUid = value;
		}	
		public Long getVisitorUid() {
			return this.visitorUid;
		}
		public void setBvisitorUid(Long value) {
			this.bvisitorUid = value;
		}	
		public Long getBvisitorUid() {
			return this.bvisitorUid;
		}
		public void setVisitorTime(Date value) {
			this.visitorTime = value;
		}	
		public Date getVisitorTime() {
			return this.visitorTime;
		}
}
