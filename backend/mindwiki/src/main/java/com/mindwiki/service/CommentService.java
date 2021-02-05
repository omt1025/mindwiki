package com.mindwiki.service;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 댓글 서비스 인터페이스
* 최종 수정일: 2021.02.04.
*******************************************************************************/
import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.CommentDto;

public interface CommentService {
	void make(CommentDto comment) throws SQLException; //생성
	public List<CommentDto> read(int MindID) throws SQLException; 
	void updateByCommentID(CommentDto comment) throws SQLException;
	void deleteByCommentID(CommentDto comment) throws SQLException;
}
