package com.cx.chenxing.model.user.result;

import com.cx.chenxing.utils.mybatisutils.PageCondition;

public class UserBean extends PageCondition {

	private Long id;
	private String account;
	private String password;
	private String phone;
	private String userName;
	private String mail;
	private Integer age;
	private String sex;
	private String img;
	private String loginTime;
	private String creTime;
	private String activate;
	private String adress;
	private String birthday;
	private String signature;
	private Integer accountLevel;//账号等级

	public void setAccountLevel(Integer value){
		this.accountLevel = value;
	}
	public Integer getAccountLevel(){
		return this.accountLevel;
	}
		public void setId(Long value) {
			this.id = value;
		}	
		public Long getId() {
			return this.id;
		}
		public void setAccount(String value) {
			this.account = value;
		}	
		public String getAccount() {
			return this.account;
		}
		public void setPassword(String value) {
			this.password = value;
		}	
		public String getPassword() {
			return this.password;
		}
		public void setPhone(String value) {
			this.phone = value;
		}	
		public String getPhone() {
			return this.phone;
		}
		public void setUserName(String value) {
			this.userName = value;
		}
		public String getUserName() {
			return this.userName;
		}
		public void setMail(String value) {
			this.mail = value;
		}	
		public String getMail() {
			return this.mail;
		}
		public void setAge(Integer value) {
			this.age = value;
		}	
		public Integer getAge() {
			return this.age;
		}
		public void setSex(String value) {
			this.sex = value;
		}	
		public String getSex() {
			return this.sex;
		}
		public void setImg(String value) {
			this.img = value;
		}	
		public String getImg() {
			return this.img;
		}
		public void setLoginTime(String value) {
			this.loginTime = value;
		}	
		public String getLoginTime() {
			return this.loginTime;
		}
		public void setCreTime(String value) {
			this.creTime = value;
		}	
		public String getCreTime() {
			return this.creTime;
		}
		public void setActivate(String value) {
			this.activate = value;
		}	
		public String getActivate() {
			return this.activate;
		}
		public void setAdress(String value) {
			this.adress = value;
		}	
		public String getAdress() {
			return this.adress;
		}
		public void setBirthday(String value) {
			this.birthday = value;
		}	
		public String getBirthday() {
			return this.birthday;
		}
		public void setSignature(String value) {
			this.signature = value;
		}	
		public String getSignature() {
			return this.signature;
		}
}
