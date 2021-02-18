/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : mind
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.ActiveDto;
import com.mindwiki.model.LikeDto;

import com.mindwiki.model.MindDto;
import com.mindwiki.model.ScrapDto;



@Repository
public interface MindDao {

	void make(MindDto mind) throws SQLException;
	void makeHashtag(int MindID,String hashtag) throws SQLException;
	public List<MindDto> read() throws SQLException;
	public MindDto readByMindID(int no) throws SQLException;
	void updateByMindID(MindDto mind) throws SQLException;
	void deleteByMindID(MindDto mind) throws SQLException;
	void updateViewCnt(int no) throws SQLException;
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
	
	void likePlusCnt(int no) throws SQLException;
	void likeMinusCnt(int no) throws SQLException;
	void scrapPlusCnt(int no) throws SQLException;
	void scrapMinusCnt(int no) throws SQLException;
	
	String getMindPorfilePic(int no) throws SQLException;
	public List<ActiveDto> readActiveList(String email) throws SQLException;
	public void updateThumbNail(String thumbnail, int MindID) throws SQLException;
	
}
