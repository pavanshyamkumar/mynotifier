package com.xadmin.usermanagement.bean;

public class Loginbeam {
	
	private String username;
	private String password;
	
	
	
	
	public Loginbeam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Loginbeam(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
