package com.mindwiki.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.ProfileDto;

@Repository
public interface ProfileDao {
	public int register(ProfileDto dto) throws SQLException;
	public int sameEmailCnt(ProfileDto dto) throws SQLException;
	
	public int withdrawal(ProfileDto dto) throws SQLException;
	public int checkPassword(ProfileDto dto) throws SQLException;
	
	public int getProfileID(ProfileDto dto) throws SQLException;
	
	
	public int updatePassword(ProfileDto dto) throws SQLException;
	public int updateProfile(ProfileDto dto) throws SQLException;
	

	
//	public String getPassword(ProfileDto dto) throws SQLException;
	
	
//	public boolean exists(String email) throws SQLException;
//	public String getPassword(String email);

}
