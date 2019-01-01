package com.board.dto;

public class boardDTO {
	private String title;
	private String content;
	private String date;
	private String id;
	private String like;
	private String disLike;
	private String bId;
	
	public String getbId() {
		return bId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	
	public void setbId(String bId) {
		this.bId = bId;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getDisLike() {
		return disLike;
	}
	public void setDisLike(String disLike) {
		this.disLike = disLike;
	}
}
