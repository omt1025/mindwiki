package com.mindwiki.service;

import java.sql.SQLException;

import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

public interface NodeService {
	
	public NodeResultDto initNode(int MindID, String subject, String hashtag) throws SQLException;
	
	public NodeResultDto setNode(NodeDto dto) throws SQLException;
	public NodeResultDto getNode(NodeDto dto) throws SQLException;
	

}
