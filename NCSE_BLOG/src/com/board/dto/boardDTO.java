package com.board.dto;

public class boardDTO {
	private String title;
	private String content;
	private String date;
	private String id;
	private int like;
	private int disLike;
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
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDisLike() {
		return disLike;
	}
	public void setDisLike(int disLike) {
		this.disLike = disLike;
	}
}
