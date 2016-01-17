package com.fantasybaby.hibernateValidtor.bean;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserLogin {

	 @NotBlank
	 @Size(max=10,message="用户名称过长")
	 private String username;
	 @NotBlank
	 private String password;
	 @DecimalMax("4")
	 private int remember;
	 public UserLogin() {
	   super();
	}
	 
	public UserLogin(String username, String password, int remember) {
		super();
		this.username = username;
		this.password = password;
		this.remember = remember;
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
	public int getRemember() {
		return remember;
	}
	public void setRemember(int remember) {
		this.remember = remember;
	}
}
