package br.com.chatbot.twitter.entity;

import java.util.UUID;

public class User {
	private UUID userId;
	private String userName;
	private int userFollowerCount;
	
	public User(String userName, int userFollowerCount) {
		super();
		this.userId = UUID.randomUUID();
		this.userName = userName;
		this.userFollowerCount = userFollowerCount;
	}
	
	public UUID getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserFollowerCount() {
		return userFollowerCount;
	}
	public void setUserFollowerCount(int userFollowerCount) {
		this.userFollowerCount = userFollowerCount;
	}
	
}
