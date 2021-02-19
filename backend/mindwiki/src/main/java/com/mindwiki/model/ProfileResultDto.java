package com.mindwiki.model;

public class ProfileResultDto {
	private String result;
	private ProfileDto profileDto;

	public ProfileDto getProfileDto() {
		return profileDto;
	}

	public void setProfileDto(ProfileDto profileDto) {
		this.profileDto = profileDto;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}