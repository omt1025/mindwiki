package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.LikeDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.model.ScrapDto;

public interface MindService {

	void make(MindDto mind) throws SQLException;
	public List<MindDto> read() throws SQLException;
	public MindDto readByMindID(int no) throws SQLException;
	void update(MindDto mind) throws SQLException;
	void delete(MindDto mind) throws SQLException;
	void scrap(int no, String email) throws SQLException;
	void like(int no, String email) throws SQLException;
	public List<ScrapDto> scrapRead(String email) throws SQLException;
	public List<LikeDto> likeRead(String email) throws SQLException;
	public List<MindDto> readByEmail(String email) throws SQLException;
	
	
}
