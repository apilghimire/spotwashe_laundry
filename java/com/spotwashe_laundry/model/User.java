package com.spotwashe_laundry.model;


public class User {

	
	int userId;
	String userName;
	Long number;
	String email ;
	String dateOfBirth ;
	String userAddress;
	String role;
	String password;
	
	public User(){}
	
	public User(int userId, String userName, Long number, String email, String dateOfBirth, String userAddress,
			String role, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.number = number;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.userAddress = userAddress;
		this.role = role;
		this.password = password;
	}
	
	
	
	public User(String userName, Long number, String email, String dateOfBirth, String userAddress, String role,
			String password) {
		super();
		this.userName = userName;
		this.number = number;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.userAddress = userAddress;
		this.role = role;
		this.password = password;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
