/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : Ranking
* 최종 수정일: 2021.02.04.
*******************************************************************************/

package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.MindDto;

@Repository
public interface LiveRankingDao {

	
	public List<MindDto> liveRankingList() throws SQLException;
	
}
