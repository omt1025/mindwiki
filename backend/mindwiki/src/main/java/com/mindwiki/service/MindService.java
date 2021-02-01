package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import com.mindwiki.model.MindDto;

public interface MindService {

	void make(MindDto mind) throws SQLException;
	public List<MindDto> read() throws SQLException;
	public MindDto readByMindID(int no) throws SQLException;
	void update(MindDto mind) throws SQLException;
	void delete(MindDto mind) throws SQLException;
}
