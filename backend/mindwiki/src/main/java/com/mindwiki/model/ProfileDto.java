package com.mindwiki.model;

//데이터로 치면 profile
public class ProfileDto {

	private int ProfileId;
	private String identification; // 추후 제거
	private String password;
	private String email;
	private String phoneNumber;
	private String follower;
	private String realName;
	private String nickName;
	private int BookShelfID;
	private int AchievementsID;
	private int ExpertID;

	private String hashtag; // 추가.
	
	private String profileDefaultPic;

	public ProfileDto() {
		super();
	};

	public ProfileDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public ProfileDto(String email, String password, String realName, String nickName) {
		super();
		this.password = password;
		this.email = email;
		this.realName = realName;
		this.nickName = nickName;
	}

	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getProfileId() {
		return ProfileId;
	}
	public void setProfileId(int profileId) {
		ProfileId = profileId;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getBookShelfID() {
		return BookShelfID;
	}
	public void setBookShelfID(int bookShelfID) {
		BookShelfID = bookShelfID;
	}
	public int getAchievementsID() {
		return AchievementsID;
	}
	public void setAchievementsID(int achievementsID) {
		AchievementsID = achievementsID;
	}
	public int getExpertID() {
		return ExpertID;
	}
	public void setExpertID(int expertID) {
		ExpertID = expertID;
	}
	public String getProfileDefaultPic() {
		return profileDefaultPic;
	}
	public void setProfileDefaultPic(String profileDefaultPic) {
		this.profileDefaultPic = profileDefaultPic;
	}

	@Override
	public String toString() {
		return "ProfileDto [ProfileId=" + ProfileId + ", identification=" + identification + ", password=" + password
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", follower=" + follower + ", realName="
				+ realName + ", nickName=" + nickName + ", BookShelfID=" + BookShelfID + ", AchievementsID="
				+ AchievementsID + ", ExpertID=" + ExpertID + ", hashtag=" + hashtag + ", profileDefaultPic="
				+ profileDefaultPic + "]";
	}

	
}
