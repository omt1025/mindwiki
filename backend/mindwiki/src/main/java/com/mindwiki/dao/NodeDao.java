package com.mindwiki.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.NodeDto;

@Repository
public interface NodeDao {
	public int existByMindID(NodeDto dto) throws SQLException;
	public void setNode(String nodeObject, int MindID) throws SQLException;
	public int initNode(NodeDto dto) throws SQLException;
	public String getNode(NodeDto dto) throws SQLException;
	
}
