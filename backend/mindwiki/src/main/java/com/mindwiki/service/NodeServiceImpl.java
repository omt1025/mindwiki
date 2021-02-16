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
		
//		String nodeString = buildInitNodeToString(MindID, subject, hashtag);
		
		NodeDto dto = new NodeDto();
		dto.setMindID(MindID);
		dto.setNodeString(builInitNodeToString(subject, hashtag));
		
		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}
		
		int insertResult = nodeMapper.setNode(dto);
		System.out.println("insertResult: " + insertResult);
		if(insertResult != SUCCESS) {
			result.setResult("SET_NODES_ERROR");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
		
	}
	
	private String nodeToString(int MindID, String subject, String hashtag) {
		JSONParser parser = new JSONParser();
		
		
		
		return null;
	}
	
	private String builInitNodeToString(String subject, String hashtag) {
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
		if(insertResult != SUCCESS) {
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
		
		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}
		
		String nodeString = nodeMapper.getNode(dto);
		if(nodeString==null) {
			result.setResult("GET_NODE_ERROR_NODE_IS_NULL");
			return result;
		}
		
//		JSONArray nodeJson = new JSONArray(nodeString);
		JSONArray nodeJson = new JSONArray();
		nodeJson.add(nodeString);
		
		System.out.println("NODE JSON");
		System.out.println(nodeJson);
		System.out.println("NODE STRING");
		System.out.println(nodeString);
		
		nodeDto.setNodeJson(nodeJson);
		result.setNodeDto(nodeDto);
		result.setResult("SUCCESS");
		return result;
			
//			public List<String> hashtagByMindID(int MindID) throws SQLException {
//
//			return session.getMapper(MindDao.class).hashtagByMindID(MindID);
		
//		JSONArray nodeJson = nodeMapper.getNode(dto);
//		nodeDto.setNodeJson(nodeJson);
//		result.setNodeDto(nodeDto);
//		
//		result.setResult("SUCCESS");
//		return result;
	}

	
//	private void initNode(int MindID, String subject, String hashtag) throws SQLException {
//		NodeDto nodeDto = new NodeDto();
//		nodeDto.setMindID(MindID);
//		JSONArray nodeJson = buildNodeJSONArray(subject, hashtag);
//		nodeDto.setNodeJson(nodeJson);
//		
//		System.out.println(nodeJson);
//		NodeResultDto result = nodeService.setNode(nodeDto);
//		System.out.println(result.getResult());
////		nodeDto.setNodeJson(buildNodeData(subject, hashtag));
//	}
//	
//	private JSONArray buildNodeJSONArray(String subject, String hashtag) {
//		JSONObject hashtagObj, nodeObj;
//		JSONArray hashtagArray = new JSONArray(), allNodeArray = new JSONArray();
//		
//		StringTokenizer st = new StringTokenizer(hashtag, ",");
//		while(st.hasMoreTokens()) {
//			hashtagObj = new JSONObject();
//			hashtagObj.put("label", st.nextToken());
//			hashtagObj.put("reason", String.valueOf(0));
//			hashtagArray.add(hashtagObj);
//		}
//		
//		nodeObj = new JSONObject();
//		nodeObj.put("label", subject);
//		nodeObj.put("root", true);
//		nodeObj.put("url", "");
//		nodeObj.put("children", hashtagArray);
//		
//		allNodeArray.add(nodeObj);
//		
//		return allNodeArray;
//	}

}

