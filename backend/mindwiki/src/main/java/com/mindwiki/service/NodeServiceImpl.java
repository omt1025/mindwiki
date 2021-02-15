package com.mindwiki.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.NodeDao;
import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;

@Service
public class NodeServiceImpl implements NodeService{
	
	@Autowired
	private SqlSession session;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public NodeResultDto setNode(NodeDto dto) throws SQLException {
		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);
		
		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}
		
		if(nodeMapper.setNode(dto)!=0) {
			result.setResult("SET_NODES_ERROR");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public NodeResultDto getNode(NodeDto dto) throws SQLException {
		NodeResultDto result = new NodeResultDto();
		NodeDto nodeDto = new NodeDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);
		
		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}
		
		String data = nodeMapper.getNode(dto);
		nodeDto.setNodeData(data);
		result.setNodeDto(nodeDto);
		
		result.setResult("SUCCESS");
		return result;
	}

}

