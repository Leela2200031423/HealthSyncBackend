package com.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin_Data")
public class Admin {
	@Id
	@Column(nullable = false)
	private int id;
	@Column(nullable = false, length = 50)
	private String email;
	@Column(nullable = false, length = 255)
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
}
