package com.tzl.entity;

public class Chat {
	private  int id;
	private  String info;
	private  String time;
	private  String sender;
	private  String color;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Chat(int id, String info, String time, String sender, String color) {
		super();
		this.id = id;
		this.info = info;
		this.time = time;
		this.sender = sender;
		this.color = color;
	}
	public Chat(String info, String time, String sender, String color) {
		super();
		this.info = info;
		this.time = time;
		this.sender = sender;
		this.color = color;
	}
	public Chat() {
		super();
	}
	
}
