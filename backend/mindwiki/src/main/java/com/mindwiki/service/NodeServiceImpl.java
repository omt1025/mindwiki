package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mindwiki.dao.MindDao;
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
	public NodeResultDto initNode(int MindID, String subject, String hashtag) throws SQLException {
		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);

		NodeDto dto = new NodeDto();
		dto.setMindID(MindID);
		dto.setNodeString(builInitNodeToString(subject, hashtag));

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}

		int insertResult = nodeMapper.initNode(dto);
		System.out.println("insertResult: " + insertResult);
		if(insertResult != SUCCESS) {
			result.setResult("SET_NODES_ERROR");
			return result;
		}

		result.setResult("SUCCESS");
		return result;
	}

	private String builInitNodeToString(String subject, String hashtag) {
		StringBuilder data = new StringBuilder();
		data.append("[\r\n"
				+ "{\"childred\": \r\n[");

		StringTokenizer st = new StringTokenizer(hashtag, ",");
		while(st.hasMoreTokens()) {
			data.append("{\"reason\":\"0\",\"label\":\"" + st.nextToken() + "\"}");
			if(!st.hasMoreTokens()) {
				data.append("]");
			}
			data.append(",\r\n");
		}
		
		data.append("\"root\":\"true\",\r\n");
		data.append("\"label\":\"" + subject + "\",\r\n");
		data.append("\"url\":\"\"}\r\n]");

//		return data.toString();
		StringBuilder data2 = new StringBuilder();
		data2.append("[\r\n" + 
				"    {\"children\":\r\n" + 
				"        [{\"reason\":\"0\",\"label\":\"0217_1643\"},\r\n" + 
				"        {\"reason\":\"0\",\"label\":\"0217_1643\"},\r\n" + 
				"        {\"reason\":\"0\",\"label\":\"0217_1643\"}],\r\n" + 
				"    \"root\":\"true\",\r\n" + 
				"    \"label\":\"0217_1643\",\r\n" + 
				"    \"url\":\"\"}\r\n" + 
				"]");
		return data2.toString();
	}

	private String builInitNodeToString_old(String subject, String hashtag) {
		StringBuilder data = new StringBuilder();
		data.append("[{ label: '" + subject + "', root:true, reason:0, url:'', children: [");

		StringTokenizer st = new StringTokenizer(hashtag, ",");
		while(st.hasMoreTokens()) {
			data.append("{ label: '" + st.nextToken() + "', reason:0, },");
		}
		data.append("],},]");

		return data.toString();
	}

	@Override
	public NodeResultDto setNode(NodeDto dto) throws SQLException {
		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}

		int insertResult = nodeMapper.setNode(dto);
		System.out.println("insertResult: " + insertResult);

		if(nodeMapper.setNode(dto)!=SUCCESS) {
			result.setResult("SET_NODES_ERROR");
			return result;
		}

		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public NodeResultDto getNode(NodeDto dto) throws SQLException {
		NodeDto nodeDto = new NodeDto();
		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);

		System.out.println("NodeServiceImpl]");

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			System.out.println("\t existByMindID error");
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}

		String nodeString = nodeMapper.getNode(dto);
		if(nodeString==null) {
			System.out.println("\t getNode error");
			result.setResult("GET_NODE_ERROR_NODE_IS_NULL");
			return result;
		}
		System.out.println("\t success");

		Gson gson = new Gson();

		System.out.println("nodeString in getNode.NodeServiceImpl");
		System.out.println(nodeString);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(gson.toJson(nodeString));

		nodeDto.setNodeString(nodeString);
		result.setNodeDto(nodeDto);
		result.setResult("SUCCESS");
		return result;
	}

}

