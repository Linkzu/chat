package com.tzl.entity;

public class Users {
	private String account;
	private String pwd;
	private int type;
	private int online;
	private String sex;
	private String nickName;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Users(String account, String pwd, int type, int online, String sex, String nickName) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.type = type;
		this.online = online;
		this.sex = sex;
		this.nickName = nickName;
	}
	public Users() {
		super();
	}
	
}
