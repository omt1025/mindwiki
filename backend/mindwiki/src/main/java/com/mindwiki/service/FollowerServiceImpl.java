/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 서비스
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.FollowerDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.MindDto;

@Service
public class FollowerServiceImpl implements FollowerService {

	@Autowired
	private SqlSession session;
	
	@Override
	public void save(FollowerDto followInfo) throws SQLException {
		session.getMapper(FollowerDao.class).save(followInfo);
	}

	@Override
	public List<FollowerDto> list(String myEmail) throws SQLException {
		
		return session.getMapper(FollowerDao.class).list(myEmail);
	}

	@Override
	public List<MindDto> listDetail(String followerEmail) throws SQLException {
		
		return session.getMapper(MindDao.class).readByEmail(followerEmail);
	}

	@Override
	public void deleteByFollower(FollowerDto followInfo) throws SQLException {
		session.getMapper(FollowerDao.class).deleteByFollower(followInfo);
	}

	@Override
	public String searchNameByEmail(String followerEmail) throws SQLException {
		
		
		return session.getMapper(FollowerDao.class).searchNameByEmail(followerEmail);
	}


	@Override
	public List<String> followerList(String myEmail) throws SQLException {
		return session.getMapper(FollowerDao.class).followerList(myEmail);
	}

	@Override
	public String getProfilePic(String email) throws SQLException {
		
		return session.getMapper(FollowerDao.class).getProfilePic(email);
	}

}
