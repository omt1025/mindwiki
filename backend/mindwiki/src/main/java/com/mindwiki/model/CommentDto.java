package com.mindwiki.model;

import java.util.Date;

public class CommentDto {

	private int commentID;
	private int MindID;
	private String data;
	private String email;
	private Date createTime;
	
	public CommentDto() {
		
	}
	
	public CommentDto(int mindID, String data, String email) {
		super();
		MindID = mindID;
		this.data = data;
		this.email = email;
	}
	public CommentDto(int mindID,int commentID, String data, String email) {
		super();
		MindID = mindID;
		this.commentID=commentID;
		this.data = data;
		this.email = email;
	}

	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getMindID() {
		return MindID;
	}
	public void setMindID(int mindID) {
		MindID = mindID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CommentDto [commentID=" + commentID + ", MindID=" + MindID + ", data=" + data + ", email=" + email
				+ ", createTime=" + createTime + "]";
	}
	
	
	
}
