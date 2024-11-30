package com.itransition.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	String name;
	@Column(unique = true, nullable = false)
	String email;
	@Column(nullable = false)
	String password;
	@Column(name = "last_login")
	private LocalDateTime lastLogin;
	Boolean blocked;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	
	public User(Integer id, String name, String email, String password, LocalDateTime lastLogin, Boolean blocked) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.lastLogin = lastLogin;
		this.blocked = blocked;
	}
	public User() {
		super();
	}
	
}
