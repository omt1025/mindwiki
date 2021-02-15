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

import com.mindwiki.dao.LiketagDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.MindDto;
@Service
public class LiketagServiceImpl implements LiketagService {

	@Autowired
	private SqlSession session;
	
	@Override
	public String list(String email) throws SQLException {

		return session.getMapper(LiketagDao.class).list(email);
	}

	@Override
	public void update(String hashtag, String email) throws SQLException {
		session.getMapper(LiketagDao.class).update(hashtag, email);
		
	}

	@Override
	public List<MindDto> likeTagAndMind(String hashtag) throws SQLException {
		return session.getMapper(LiketagDao.class).likeTagAndMind(hashtag);
	}

}
