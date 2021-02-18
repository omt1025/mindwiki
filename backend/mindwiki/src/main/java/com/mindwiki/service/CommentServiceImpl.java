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

import com.mindwiki.dao.CommentDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.CommentDto;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private SqlSession session;
	
	@Override
	public void make(CommentDto comment) throws SQLException {
		session.getMapper(CommentDao.class).make(comment);
		
	}

	@Override
	public List<CommentDto> read(int MindID) throws SQLException {
		return session.getMapper(CommentDao.class).read(MindID);
	}

	@Override
	public void updateByCommentID(CommentDto comment) throws SQLException {
		session.getMapper(CommentDao.class).updateByCommentID(comment);
		
	}

	@Override
	public void deleteByCommentID(CommentDto comment) throws SQLException {
		session.getMapper(CommentDao.class).deleteByCommentID(comment);
		
	}

}
