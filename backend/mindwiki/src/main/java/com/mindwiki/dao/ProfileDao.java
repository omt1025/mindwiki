package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

@Repository
public interface ProfileDao {
	public int register(ProfileDto dto) throws SQLException;
	public int sameEmailCnt(ProfileDto dto) throws SQLException;
	
	public int withdrawal(ProfileDto dto) throws SQLException;
	public int checkPassword(ProfileDto dto) throws SQLException;
	
	public int getProfileID(ProfileDto dto) throws SQLException;
	
	public int updatePassword(ProfileDto dto) throws SQLException;
	public int updateProfile(ProfileDto dto) throws SQLException;
	
	public List<ProfileDto> memberList() throws SQLException;
	
	public String getProfilePic(String email) throws SQLException;
	
	public ProfileDto getProfile(String email) throws SQLException;
	public ProfileDto getMyProfile(String email) throws SQLException;
	
	
	
	
	public int updateProfileDefaultPic(ProfileDto dto) throws SQLException;
	public int updatePhonenumber(ProfileDto dto) throws SQLException;
	public int updateNickname(ProfileDto dto) throws SQLException;
	
	
	
//	public String getPassword(ProfileDto dto) throws SQLException;
	
	
//	public boolean exists(String email) throws SQLException;
//	public String getPassword(String email);

}
