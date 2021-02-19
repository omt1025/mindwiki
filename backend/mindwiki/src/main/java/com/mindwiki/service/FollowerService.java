package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.MindDto;

public interface FollowerService {

	
	void save(FollowerDto followInfo) throws SQLException; //생성
	public List<FollowerDto> list(String myEmail) throws SQLException; 
	List<MindDto> listDetail(String followerEmail) throws SQLException;
	void deleteByFollower(FollowerDto followInfo) throws SQLException;
	String searchNameByEmail(String followerEmail) throws SQLException;
	public List<String> followerList(String myEmail) throws SQLException;
	public String getProfilePic(String email) throws SQLException;
	
}
