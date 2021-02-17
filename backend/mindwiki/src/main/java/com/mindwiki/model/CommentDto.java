/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 코멘트
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.model;

import java.util.Date;

public class CommentDto {

	private int CommentID;
	private int MindID;
	private String data;
	private String email;
	private Date createTime;
	
	
	private String profileDefaultPic;
	
	
	public String getProfileDefaultPic() {
		return profileDefaultPic;
	}

	public void setProfileDefaultPic(String profileDefaultPic) {
		this.profileDefaultPic = profileDefaultPic;
	}

	public CommentDto() {
		
	}
	
	public CommentDto(int mindID, String data, String email) {
		super();
		MindID = mindID;
		this.data = data;
		this.email = email;
	}
	public CommentDto(int mindID,int CommentID, String data, String email) {
		super();
		MindID = mindID;
		this.CommentID=CommentID;
		this.data = data;
		this.email = email;
	}

	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int CommentID) {
		this.CommentID = CommentID;
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
		return "CommentDto [CommentID=" + CommentID + ", MindID=" + MindID + ", data=" + data + ", email=" + email
				+ ", createTime=" + createTime + ", profileDefaultPic=" + profileDefaultPic + "]";
	}
	
	
	
	
	
}
