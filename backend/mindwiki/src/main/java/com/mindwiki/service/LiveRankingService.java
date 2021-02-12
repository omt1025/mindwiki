/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 실시간 랭킹
* 최종 수정일: 2021.02.12.
*******************************************************************************/
package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.MindDto;

public interface LiveRankingService {
	public List<MindDto> liveRankingList() throws SQLException;
}
