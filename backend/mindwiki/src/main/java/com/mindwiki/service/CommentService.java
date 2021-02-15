package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.CommentDto;

public interface CommentService {
	void make(CommentDto comment) throws SQLException; //생성
	public List<CommentDto> read(int MindID) throws SQLException; 
	void updateByCommentID(CommentDto comment) throws SQLException;
	void deleteByCommentID(CommentDto comment) throws SQLException;
}
