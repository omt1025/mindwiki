package com.mindwiki.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.NodeDto;

@Repository
public interface NodeDao {
	public int existByMindID(NodeDto dto) throws SQLException;
	public int setNode(NodeDto dto) throws SQLException;
	public NodeDto getNode(NodeDto dto) throws SQLException;
}
