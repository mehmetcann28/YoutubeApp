package com.mehmetc.dto.request;

import com.mehmetc.entity.enums.ERole;

public class UserSaveRequestDTO {
	private Long userId;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	private ERole role;
	
	public UserSaveRequestDTO(String name, String surname, String email, String username, String password, ERole role) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UserSaveRequestDTO(Long userId, String name, String surname, String email, String username, String password, ERole role) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public ERole getRole() {
		return role;
	}
	
	public void setRole(ERole role) {
		this.role = role;
	}
}