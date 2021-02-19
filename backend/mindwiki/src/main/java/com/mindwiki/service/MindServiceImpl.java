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

import com.mindwiki.dao.LoginDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.ActiveDto;
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
	public MindDto readByMindIDNoCount(int no) throws SQLException {
		return session.getMapper(MindDao.class).readByMindID(no);

	}

	@Override
	public void update(MindDto mind) throws SQLException {
		session.getMapper(MindDao.class).updateByMindID(mind);

	}

	@Override
	public void delete(MindDto mind) throws SQLException {
		session.getMapper(MindDao.class).deleteByMindID(mind);
		session.getMapper(MindDao.class).deleteHashtagList(mind.getMindID());
		// 마인드삭제할때 리스트까지 다 날라가게

	}

	@Override
	public List<MindDto> readByEmail(String email) throws SQLException {
		return session.getMapper(MindDao.class).readByEmail(email);
	}

	@Override
	public void scrap(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).scrap(no, email);
		session.getMapper(MindDao.class).scrapPlusCnt(no);
	}

	@Override
	public void like(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).like(no, email);
		session.getMapper(MindDao.class).likePlusCnt(no);

	}

	@Override
	public List<MindDto> scrapRead(String email) throws SQLException {

		return session.getMapper(MindDao.class).scrapRead(email);
	}

	@Override
	public List<MindDto> likeRead(String email) throws SQLException {

		return session.getMapper(MindDao.class).likeRead(email);

	}

	@Override
	public void deleteLike(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).deleteLike(no, email);
		session.getMapper(MindDao.class).likeMinusCnt(no);

	}

	@Override
	public void deleteScrap(int no, String email) throws SQLException {
		session.getMapper(MindDao.class).deleteScrap(no, email);
		session.getMapper(MindDao.class).scrapMinusCnt(no);
	}

	@Override
	public int getMindID(String email) throws SQLException {
		return session.getMapper(MindDao.class).getMindID(email);
	}

	@Override
	public void makeHashtag(int MindID, String hashtag) throws SQLException {
		session.getMapper(MindDao.class).makeHashtag(MindID, hashtag);

	}

	@Override
	public List<String> hashtagByMindID(int MindID) throws SQLException {

		return session.getMapper(MindDao.class).hashtagByMindID(MindID);
	}

	@Override
	public void deleteHashtag(int MindID, String hashtag) throws SQLException {
		session.getMapper(MindDao.class).deleteHashtag(MindID, hashtag);

	}

	@Override
	public void deleteHashtagList(int MindID) throws SQLException {
		session.getMapper(MindDao.class).deleteHashtagList(MindID);

	}

	@Override
	public String getMindPorfilePic(int no) throws SQLException {
		return session.getMapper(MindDao.class).getMindPorfilePic(no);

	}

	@Override
	public List<ActiveDto> readActiveList(String email) throws SQLException {
		return session.getMapper(MindDao.class).readActiveList(email);
	}
	
	@Override
	public void updateThumbNail(String thumbnail, int MindID) throws SQLException {

		session.getMapper(MindDao.class).updateThumbNail(thumbnail, MindID);
		
	}


}
