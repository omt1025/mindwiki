
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 마인드
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.model;

import java.util.Date;

public class MindDto {

	private int MindID;
	private String admin;
	private String initCreateTime;
	private String title;
	private String hashtag;
	private int hashtagCnt;
	private String writeAuthority;
	private String writeAuthorityLevel;
	private String readAuthority;
	private String readAuthorityLevel;
	private String scrapAuthority;
	private String scrapAuthorityLevel;
	private int viewCnt;
	private int scrapCnt;
	private String comment;
	private int likeCnt;
	
	private int nodeCnt;
	private int NodeListID;
	private int TimeTagID;
			
	private String thumbnail;
	private String subject; //이거 추가됨 //db에는 alter table로 추가해줬음
	private String explanation;//이거도 추가됨
	
	private String profileDefaultPic;
	
	public String getProfileDefaultPic() {
		return profileDefaultPic;
	}


	public void setProfileDefaultPic(String profileDefaultPic) {
		this.profileDefaultPic = profileDefaultPic;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public void setInitCreateTime(String initCreateTime) {
		this.initCreateTime = initCreateTime;
	}

	public MindDto() {
		super();
	}
	public MindDto(String admin, String title, String hashtag, String subject, String explanation) {
		super();
		this.admin = admin;
		this.title = title;
		this.hashtag = hashtag;
		this.subject = subject;
		this.explanation = explanation;
	}
	
	
	public MindDto(int MindID, String title, String hashtag, String subject, String explanation) {
		super();
		this.MindID=MindID;
		this.title = title;
		this.hashtag = hashtag;
		this.subject = subject;
		this.explanation = explanation;
	}


	public String getInitCreateTime() {
		return initCreateTime;
	}
	public int getMindID() {
		return MindID;
	}
	public void setMindID(int mindID) {
		MindID = mindID;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getHashtagCnt() {
		return hashtagCnt;
	}
	public void setHashtagCnt(int hashtagCnt) {
		this.hashtagCnt = hashtagCnt;
	}
	public String getWriteAuthority() {
		return writeAuthority;
	}
	public void setWriteAuthority(String writeAuthority) {
		this.writeAuthority = writeAuthority;
	}
	public String getWriteAuthorityLevel() {
		return writeAuthorityLevel;
	}
	public void setWriteAuthorityLevel(String writeAuthorityLevel) {
		this.writeAuthorityLevel = writeAuthorityLevel;
	}
	public String getReadAuthority() {
		return readAuthority;
	}
	public void setReadAuthority(String readAuthority) {
		this.readAuthority = readAuthority;
	}
	public String getReadAuthorityLevel() {
		return readAuthorityLevel;
	}
	public void setReadAuthorityLevel(String readAuthorityLevel) {
		this.readAuthorityLevel = readAuthorityLevel;
	}
	public String getScrapAuthority() {
		return scrapAuthority;
	}
	public void setScrapAuthority(String scrapAuthority) {
		this.scrapAuthority = scrapAuthority;
	}
	public String getScrapAuthorityLevel() {
		return scrapAuthorityLevel;
	}
	public void setScrapAuthorityLevel(String scrapAuthorityLevel) {
		this.scrapAuthorityLevel = scrapAuthorityLevel;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getScrapCnt() {
		return scrapCnt;
	}
	public void setScrapCnt(int scrapCnt) {
		this.scrapCnt = scrapCnt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getNodeCnt() {
		return nodeCnt;
	}
	public void setNodeCnt(int nodeCnt) {
		this.nodeCnt = nodeCnt;
	}
	public int getNodeListID() {
		return NodeListID;
	}
	public void setNodeListID(int nodeListID) {
		NodeListID = nodeListID;
	}
	public int getTimeTagID() {
		return TimeTagID;
	}
	public void setTimeTagID(int timeTagID) {
		TimeTagID = timeTagID;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	@Override
	public String toString() {
		return "MindDto [MindID=" + MindID + ", admin=" + admin + ", initCreateTime=" + initCreateTime + ", title="
				+ title + ", hashtag=" + hashtag + ", hashtagCnt=" + hashtagCnt + ", writeAuthority=" + writeAuthority
				+ ", writeAuthorityLevel=" + writeAuthorityLevel + ", readAuthority=" + readAuthority
				+ ", readAuthorityLevel=" + readAuthorityLevel + ", scrapAuthority=" + scrapAuthority
				+ ", scrapAuthorityLevel=" + scrapAuthorityLevel + ", viewCnt=" + viewCnt + ", scrapCnt=" + scrapCnt
				+ ", comment=" + comment + ", likeCnt=" + likeCnt + ", nodeCnt=" + nodeCnt + ", NodeListID="
				+ NodeListID + ", TimeTagID=" + TimeTagID + ", thumbnail=" + thumbnail + ", subject=" + subject
				+ ", explanation=" + explanation + ", profileDefaultPic=" + profileDefaultPic + "]";
	}

	
	
	
	
}
