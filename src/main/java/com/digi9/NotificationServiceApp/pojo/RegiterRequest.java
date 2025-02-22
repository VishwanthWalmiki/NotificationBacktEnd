package com.digi9.NotificationServiceApp.pojo;

import java.util.List;

public class RegiterRequest {
	
	private int user_id ;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private List<String> projectIds ;
	
	public int getUser_id() {
		return user_id;
	}
	public List<String> getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}
	public List<String> projectIds() {
		return projectIds;
	}
	public void setProjectId(List<String> projectIds) {
		this.projectIds = projectIds;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "RegiterRequest [user_id=" + user_id + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", projectId=" + projectIds + "]";
	}
	

}
