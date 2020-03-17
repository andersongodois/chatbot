package br.com.chatbot.twitter.entity;

import java.util.Date;
import java.util.UUID;

public class History {
	
	private UUID historyId;
	private String hashtag;
	private Date updateDate;
	
	public History(String hashtag, Date updateDate) {
		super();
		this.historyId = UUID.randomUUID();
		this.hashtag = hashtag;
		this.updateDate = updateDate;
	}
	
	public UUID getHistoryId() {
		return historyId;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Hashtag=" + hashtag + ", Data de atualização=" + updateDate;
	}
	
}
