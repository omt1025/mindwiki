package com.mindwiki.model;

public class LikeDto {
	private String email;
	private int likedMindID;
	public String getEamil() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLikedMindID() {
		return likedMindID;
	}
	public void setLikedMindID(int likedMindID) {
		this.likedMindID = likedMindID;
	}
	
	@Override
	public String toString() {
		return "LikeDto [email=" + email + ", likedMindID=" + likedMindID + "]";
	}
	

	
	
}
