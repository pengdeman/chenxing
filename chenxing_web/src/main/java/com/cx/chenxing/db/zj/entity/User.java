package com.cx.chenxing.db.zj.entity;

public class User {
    private Integer id;

    private String userName;//用户名

    private String password;

    private Integer age;
    
    private String loginName;//登录名
    
    private String mail;
    
    private String creDate;//账号创建时间
    
    private String loginIp;//上次账号登录ip
    
    private String loginDate;//上次登录时间
    
    private String loginadr;//上次登陆地址
    
    private String isActivate;//是否被激活
    
    private String pictruename;
    
    private String picurl;
    
    public String getLoginadr() {
		return loginadr;
	}

	public void setLoginadr(String loginadr) {
		this.loginadr = loginadr;
	}

	public String getPictruename() {
		return pictruename;
	}

	public void setPictruename(String pictruename) {
		this.pictruename = pictruename;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(String isActivate) {
		this.isActivate = isActivate;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String date) {
		this.creDate = date;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}