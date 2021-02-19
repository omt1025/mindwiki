package com.mindwiki.model;

public class ActiveDto {

	private int MindID;
	private String email;
	private String time;	// 스크랩,좋아요 시간
	private String title;	// 게시물 제목
	private String what;	// 좋아요 or 스크랩
	
	
	public ActiveDto() {
		super();
	}
	public ActiveDto(int mindID, String email, String time, String title, String what) {
		super();
		MindID = mindID;
		this.email = email;
		this.time = time;
		this.title = title;
		this.what = what;
	}
	public int getMindID() {
		return MindID;
	}
	public void setMindID(int mindID) {
		MindID = mindID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	@Override
	public String toString() {
		return "ActiveDto [MindID=" + MindID + ", email=" + email + ", time=" + time + ", title=" + title + ", what="
				+ what + "]";
	}
}
