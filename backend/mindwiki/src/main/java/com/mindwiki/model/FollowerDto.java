package com.mindwiki.model;

public class FollowerDto {

	private String myEmail;
	private String followerEmail;
	private String name;
	
	
	public FollowerDto() {
		super();
	}
	
	public FollowerDto(String myEmail, String followerEmail, String name) {
		super();
		this.myEmail = myEmail;
		this.followerEmail = followerEmail;
		this.name = name;
	}
	
	public String getMyEmail() {
		return myEmail;
	}
	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}
	public String getFollowerEmail() {
		return followerEmail;
	}
	public void setFollowerEmail(String followerEmail) {
		this.followerEmail = followerEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FollowerDto [myEmail=" + myEmail + ", followerEmail=" + followerEmail + ", name=" + name + "]";
	}
	
	
	
}
