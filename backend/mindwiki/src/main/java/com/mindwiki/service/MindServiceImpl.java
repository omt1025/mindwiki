package com.mindwiki.service;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : mind service implementation
* 최종 수정일: 2021.02.04.
*******************************************************************************/
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.LikeDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.model.ScrapDto;

@Service
public class MindServiceImpl implements MindService {

	@Autowired
	private SqlSession session;

	@Override
	public void make(MindDto mind) throws SQLException {
		session.getMapper(MindDao.class).make(mind);
		
	}

	@Override
	public List<MindDto> read() throws SQLException {
		return session.getMapper(MindDao.class).read();
	}

	@Override
	public MindDto readByMindID(int no) throws SQLException { 
		session.getMapper(MindDao.class).updateViewCnt(no);
		return session.getMapper(MindDao.class).readByMindID(no);
		
	}

	@Override
	public void update(MindDto mind) throws SQLException {
		session.getMapper(MindDao.class).updateByMindID(mind);
		
	}

	@Override
	public void delete(MindDto mind) throws SQLException {
		session.getMapper(MindDao.class).deleteByMindID(mind);
		
	}


	@Override
	public List<MindDto> readByEmail(String email) throws SQLException {
		return session.getMapper(MindDao.class).readByEmail(email);
	}


	@Override
	public void scrap(int no,String email) throws SQLException {
		session.getMapper(MindDao.class).scrap(no, email);
		
	}
	
	@Override
	public void like(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).like(no, email);
		
	}

	@Override
	public List<ScrapDto> scrapRead(String email) throws SQLException {
		
		return session.getMapper(MindDao.class).scrapRead(email); 
	}

	@Override
	public List<LikeDto> likeRead(String email) throws SQLException {
		
		return session.getMapper(MindDao.class).likeRead(email);
		
	}

	@Override
	public void deleteLike(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).deleteLike(no, email);
		
	}

	@Override
	public void deleteScrap(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).deleteScrap(no, email);
		
	}

	
}
