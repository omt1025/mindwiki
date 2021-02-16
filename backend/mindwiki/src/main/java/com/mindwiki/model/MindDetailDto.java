package com.mindwiki.model;

public class MindDetailDto {
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
	
	private String profileDefaultPic;	// 마인드 주인 프로필 사진.

	public MindDetailDto(int mindID, String admin, String initCreateTime, String title, String hashtag, int hashtagCnt,
			String writeAuthority, String writeAuthorityLevel, String readAuthority, String readAuthorityLevel,
			String scrapAuthority, String scrapAuthorityLevel, int viewCnt, int scrapCnt, String comment, int likeCnt,
			int nodeCnt, int nodeListID, int timeTagID, String thumbnail, String subject, String explanation,
			String profileDefaultPic) {
		super();
		MindID = mindID;
		this.admin = admin;
		this.initCreateTime = initCreateTime;
		this.title = title;
		this.hashtag = hashtag;
		this.hashtagCnt = hashtagCnt;
		this.writeAuthority = writeAuthority;
		this.writeAuthorityLevel = writeAuthorityLevel;
		this.readAuthority = readAuthority;
		this.readAuthorityLevel = readAuthorityLevel;
		this.scrapAuthority = scrapAuthority;
		this.scrapAuthorityLevel = scrapAuthorityLevel;
		this.viewCnt = viewCnt;
		this.scrapCnt = scrapCnt;
		this.comment = comment;
		this.likeCnt = likeCnt;
		this.nodeCnt = nodeCnt;
		NodeListID = nodeListID;
		TimeTagID = timeTagID;
		this.thumbnail = thumbnail;
		this.subject = subject;
		this.explanation = explanation;
		this.profileDefaultPic = profileDefaultPic;
	}

	public MindDetailDto() {
		super();
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

	public String getInitCreateTime() {
		return initCreateTime;
	}

	public void setInitCreateTime(String initCreateTime) {
		this.initCreateTime = initCreateTime;
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

	public String getProfileDefaultPic() {
		return profileDefaultPic;
	}

	public void setProfileDefaultPic(String profileDefaultPic) {
		this.profileDefaultPic = profileDefaultPic;
	}

	@Override
	public String toString() {
		return "MindDetailDto [MindID=" + MindID + ", admin=" + admin + ", initCreateTime=" + initCreateTime
				+ ", title=" + title + ", hashtag=" + hashtag + ", hashtagCnt=" + hashtagCnt + ", writeAuthority="
				+ writeAuthority + ", writeAuthorityLevel=" + writeAuthorityLevel + ", readAuthority=" + readAuthority
				+ ", readAuthorityLevel=" + readAuthorityLevel + ", scrapAuthority=" + scrapAuthority
				+ ", scrapAuthorityLevel=" + scrapAuthorityLevel + ", viewCnt=" + viewCnt + ", scrapCnt=" + scrapCnt
				+ ", comment=" + comment + ", likeCnt=" + likeCnt + ", nodeCnt=" + nodeCnt + ", NodeListID="
				+ NodeListID + ", TimeTagID=" + TimeTagID + ", thumbnail=" + thumbnail + ", subject=" + subject
				+ ", explanation=" + explanation + ", profileDefaultPic=" + profileDefaultPic + "]";
	}
	
}
