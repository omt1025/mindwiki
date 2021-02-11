package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.MindDto;

@Repository
public interface LiveRankingDao {

	
	public List<MindDto> liveRankingList() throws SQLException;
	
}
