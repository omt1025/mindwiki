/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 실시간 랭킹
* 최종 수정일: 2021.02.12.
*******************************************************************************/
package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LiveRankingDao;
import com.mindwiki.dao.MindDao;
import com.mindwiki.model.MindDto;

@Service
public class LiveRankingServiceImpl implements LiveRankingService {


	@Autowired
	private SqlSession session;
	
	@Override
	public List<MindDto> liveRankingList() throws SQLException {

		return session.getMapper(LiveRankingDao.class).liveRankingList();
	}

	
	
}
