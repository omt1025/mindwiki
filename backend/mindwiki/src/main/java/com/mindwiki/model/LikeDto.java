package com.mindwiki.model;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 좋아요 dto
* 최종 수정일: 2021.02.04.
*******************************************************************************/
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
