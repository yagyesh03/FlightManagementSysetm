package com.cpg.fms.model;

import java.util.HashMap;


public class User {

	private String userType;
	private String userName;
	private String password;
	private String eMail;
	private long userId;
	private Long phoneNumber;
	HashMap<Long, Booking> bookings = new HashMap<Long, Booking>();
	
	
	public User() {
		
	}
	
public User(String userType, String userName, String password, long userId) {
	
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.userId = userId;
	}

//	Getters and Setters:
	
	public String getUserType() {
		return userType;
	}
	public HashMap<Long, Booking> getBookings() {
		return bookings;
	}

	public void setBookings(HashMap<Long, Booking> bookings) {
		this.bookings = bookings;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
