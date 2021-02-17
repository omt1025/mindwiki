package com.mindwiki.service;

import java.sql.SQLException;

import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

public interface NodeService {
	
	public NodeResultDto initNode(int MindID, String subject, String hashtag) throws SQLException;
	
	public void setNode(String objectNode, int MindID) throws SQLException;
	public NodeResultDto setNodeObject(NodeDto dto) throws SQLException;
	public NodeResultDto getNode(NodeDto dto) throws SQLException;
	

}
