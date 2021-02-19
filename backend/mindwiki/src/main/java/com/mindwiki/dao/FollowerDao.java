/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : follower
* 최종 수정일: 2021.02.04.
*******************************************************************************/

package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.MindDto;



@Repository
public interface FollowerDao {

	
	void save(FollowerDto followInfo) throws SQLException; //생성
	public List<FollowerDto> list(String myEmail) throws SQLException; 
	List<MindDto> listDetail(String followerEmail) throws SQLException;
	void deleteByFollower(FollowerDto followInfo) throws SQLException;
	String searchNameByEmail(String followerEmail) throws SQLException;
	public List<String> followerList(String myEmail) throws SQLException;
	public String getProfilePic(String email) throws SQLException;
	
}
