/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 좋아요
* 최종 수정일: 2021.02.04.
*******************************************************************************/

package com.mindwiki.model;

public class LikeDto {
	private String email;
	private int likedMindID;
	private String time;
	
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "LikeDto [email=" + email + ", likedMindID=" + likedMindID + ", time=" + time + "]";
	}
}
