package com.board.dto;

import java.util.ArrayList;

public class boardDTO {
	private int like;
	private int disLike;
	
	private String title;
	private String content;
	private String date;
	private String id;
	private String bId;
	private String type;
	
	private ArrayList<commentDTO> comments;
	
	
	public int getLike() {
		return like;
	}
	public int getDisLike() {
		return disLike;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getDate() {
		return date;
	}
	public String getId() {
		return id;
	}
	public String getbId() {
		return bId;
	}
	public String getType() {
		return type;
	}
	public ArrayList<commentDTO> getComments() {
		return comments;
	}
	
	public void setComments(ArrayList<commentDTO> comments) {
		this.comments = comments;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public void setDisLike(int disLike) {
		this.disLike = disLike;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public void setType(String type) {
		this.type = type;
	}

}
