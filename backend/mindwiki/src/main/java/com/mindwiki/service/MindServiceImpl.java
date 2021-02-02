package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.MindDto;

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
	public void scrap(MindDto mind) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MindDto> readByEmail(String email) throws SQLException {
		return session.getMapper(MindDao.class).readByEmail(email);
	}


	
}
