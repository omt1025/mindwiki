package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

public interface ProfileService {
	public ProfileResultDto register(ProfileDto dto) throws SQLException;
	public ProfileResultDto withdrawal(ProfileDto dto) throws SQLException;
	public ProfileResultDto changePassword(ProfileDto dto, String newPassword) throws SQLException;
	public ProfileResultDto changeProfile(ProfileDto dto, MultipartFile file) throws SQLException;
	public ProfileDto getProfile(ProfileDto profileDto) throws SQLException;
	public ProfileResultDto isExist(ProfileDto profileDto) throws SQLException;
	public List<ProfileDto> getAllProfile() throws SQLException;
	public ProfileResultDto sendTempPassword(ProfileDto dto) throws SQLException;
	public String getProfilePic(String email) throws SQLException;
	public ProfileResultDto getProfile(String email) throws Exception;
	public ProfileResultDto getMyProfile(String email) throws Exception;
}
