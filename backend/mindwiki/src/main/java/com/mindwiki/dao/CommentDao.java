package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.CommentDto;

@Repository
public interface CommentDao {
//comment CRUD
	void make(CommentDto comment) throws SQLException; //생성
	public List<CommentDto> read(int MindID) throws SQLException; 
	void updateByCommentID(CommentDto comment) throws SQLException;
	void deleteByCommentID(CommentDto comment) throws SQLException;
	
}
