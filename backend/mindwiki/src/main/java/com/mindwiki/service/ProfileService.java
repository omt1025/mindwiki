package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

public interface ProfileService {
	public ProfileResultDto register(ProfileDto dto) throws SQLException;
	public ProfileResultDto withdrawal(ProfileDto dto) throws SQLException;
	public ProfileResultDto changePassword(ProfileDto dto, String newPassword) throws SQLException;
	public ProfileResultDto changeProfile(ProfileDto dto) throws SQLException;
	public ProfileDto getProfile(ProfileDto profileDto) throws SQLException;
	public ProfileResultDto isExist(ProfileDto profileDto) throws SQLException;
	public List<ProfileDto> memberList() throws SQLException;
}
