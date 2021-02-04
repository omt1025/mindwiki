package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.LikeDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.model.ScrapDto;



@Repository
public interface MindDao {

	void make(MindDto mind) throws SQLException;
	public List<MindDto> read() throws SQLException;
	public MindDto readByMindID(int no) throws SQLException;
	void updateByMindID(MindDto mind) throws SQLException;
	void deleteByMindID(MindDto mind) throws SQLException;
	void updateViewCnt(int no) throws SQLException;
	void scrap(int no, String email) throws SQLException;
	void like(int no, String email) throws SQLException;
	public List<ScrapDto> scrapRead(String email) throws SQLException;
	public List<LikeDto> likeRead(String email) throws SQLException;
	public List<MindDto> readByEmail(String email) throws SQLException;
}
