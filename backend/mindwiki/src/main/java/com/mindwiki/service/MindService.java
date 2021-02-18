package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.ActiveDto;
import com.mindwiki.model.LikeDto;

import com.mindwiki.model.MindDto;
import com.mindwiki.model.ScrapDto;

public interface MindService {

	void make(MindDto mind) throws SQLException;
	void makeHashtag(int MindID,String hashtag) throws SQLException;
	public List<MindDto> read() throws SQLException;
	public MindDto readByMindID(int no) throws SQLException;
	public MindDto readByMindIDNoCount(int no) throws SQLException;
	
	void update(MindDto mind) throws SQLException;
	void delete(MindDto mind) throws SQLException;
	void scrap(int no, String email) throws SQLException;
	void like(int no, String email) throws SQLException;
	void deleteLike(int no, String email) throws SQLException;
	void deleteScrap(int no, String email) throws SQLException;
	public List<MindDto> scrapRead(String email) throws SQLException;
	public List<MindDto> likeRead(String email) throws SQLException;
	public List<MindDto> readByEmail(String email) throws SQLException;
	int getMindID(String email) throws SQLException;
	public List<String> hashtagByMindID(int MindID) throws SQLException;
	void deleteHashtag(int MindID, String hashtag) throws SQLException;
	void deleteHashtagList(int MindID) throws SQLException;
	
	
	String getMindPorfilePic(int no) throws SQLException;
	public List<ActiveDto> readActiveList(String email) throws SQLException;
	public void updateThumbNail(String thumbnail, int MindID) throws SQLException;
}
