package br.com.chatbot.twitter.entity;

import java.util.UUID;

public class Status {
	
	private UUID statusId;
	private User user;
	private String lang;
	
	public Status(User user, String lang) {
		super();
		this.statusId = UUID.randomUUID();
		this.user = user;
		this.lang = lang;
	}
	
	public UUID getStatusId() {
		return statusId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}
