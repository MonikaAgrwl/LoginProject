package com.mycompany.myapp;


public class User {

	private String username;
	private String password;
	private boolean loggedIn;
	private long numberOfLogins;
	
	public boolean isLoggedIn() {
		//testing git
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public long getNumberOfLogins() {
		return numberOfLogins;
	}

	public void setNumberOfLogins(long numberOfLogins) {
		this.numberOfLogins = numberOfLogins;
	}

	User(String pwd)
	{
		this.password = pwd;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}