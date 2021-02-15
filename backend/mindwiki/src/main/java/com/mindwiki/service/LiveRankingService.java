package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.MindDto;

public interface LiveRankingService {
	public List<MindDto> liveRankingList() throws SQLException;
}
