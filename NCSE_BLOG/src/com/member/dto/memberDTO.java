package com.member.dto;

public class memberDTO {

	private String id;
	private String pw;
	private String name;
	private String nick;
	private String sex;
	private String email;
	private String phone;
	private boolean user_authority;
	private String platform_link;
	private String self_imp;
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public String getNick() {
		return nick;
	}
	public String getSex() {
		return sex;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public boolean getUser_authority() {
		return user_authority;
	}
	public String getPlatform_link() {
		return platform_link;
	}
	public String getSelf_imp() {
		return self_imp;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setUser_authority(boolean user_authority) {
		this.user_authority = user_authority;
	}
	public void setPlatform_link(String platform_link) {
		this.platform_link = platform_link;
	}
	public void setSelf_imp(String self_imp) {
		this.self_imp = self_imp;
	}
	
	
}
