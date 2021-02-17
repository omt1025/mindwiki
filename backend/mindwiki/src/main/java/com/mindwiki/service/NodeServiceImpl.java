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
		
		Object nodeObject = makeInitNode(subject, hashtag);
		Gson gson = new Gson();
		dto.setNodeObject(gson.toJson(nodeObject));

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}

		if(nodeMapper.initNode(dto) != SUCCESS) {
			System.out.println("INIT_NODE_ERROR: InsertResultROWNum=" + nodeMapper.initNode(dto));
			result.setResult("SET_NODES_ERROR");
			return result;
		}

		result.setResult("SUCCESS");
		return result;
	}
	
	private Object makeInitNode(String subject, String hashtag) {
		String[] hashtagArr = new String[3];
		StringTokenizer st = new StringTokenizer(hashtag, ",");
		int index=0;
		while(st.hasMoreElements()) {
			hashtagArr[index++] = st.nextToken();
		}
		
		Object obj = "[\r\n" + 
				"{\"children\":\r\n" + 
				"[{\"reason\":\"0\",\"" + hashtagArr[0] + "\":\"0217_1643\"},\r\n" + 
				"{\"reason\":\"0\",\"" +  hashtagArr[1] + "\":\"0217_1643\"},\r\n" + 
				"{\"reason\":\"0\",\"" +  hashtagArr[2] + "\":\"0217_1643\"}],\r\n" + 
				"\"root\":\"true\",\r\n" + 
				"\"label\":\"" + subject + "\",\r\n" + 
				"\"url\":\"\"}\r\n" + 
				"]";
		
		return obj;
	}

//	private Object builInitNodeToString(String subject, String hashtag) {
//		StringBuilder data = new StringBuilder();
//		data.append("[\r\n"
//				+ "{\"childred\": \r\n[");
//
//		StringTokenizer st = new StringTokenizer(hashtag, ",");
//		while(st.hasMoreTokens()) {
//			data.append("{\"reason\":\"0\",\"label\":\"" + st.nextToken() + "\"}");
//			if(!st.hasMoreTokens()) {
//				data.append("]");
//			}
//			data.append(",\r\n");
//		}
//		
//		data.append("\"root\":\"true\",\r\n");
//		data.append("\"label\":\"" + subject + "\",\r\n");
//		data.append("\"url\":\"\"}\r\n]");
//
////		return data.toString();
////		StringBuilder data2 = new StringBuilder();
////		data2.append("[\r\n" + 
////				"    {\"children\":\r\n" + 
////				"        [{\"reason\":\"0\",\"label\":\"123\"},\r\n" + 
////				"        {\"reason\":\"0\",\"label\":\"123\"},\r\n" + 
////				"        {\"reason\":\"0\",\"label\":\"123\"}],\r\n" + 
////				"    \"root\":\"true\",\r\n" + 
////				"    \"label\":\"woong\",\r\n" + 
////				"    \"url\":\"\"}\r\n" + 
////				"]");
////		return data2.toString();
//		
//		Object obj;
//		obj = "[\r\n" + 
//				"    {\"children\":\r\n" + 
//				"        [{\"reason\":\"0\",\"label\":\"0217_1643\"},\r\n" + 
//				"        {\"reason\":\"0\",\"label\":\"0217_1643\"},\r\n" + 
//				"        {\"reason\":\"0\",\"label\":\"0217_1643\"}],\r\n" + 
//				"    \"root\":\"true\",\r\n" + 
//				"    \"label\":\"0217_1643\",\r\n" + 
//				"    \"url\":\"\"}\r\n" + 
//				"]";
//		Gson gson = new Gson();
//		
//		return gson.toJson(obj);
//	}

//	private String builInitNodeToString_old(String subject, String hashtag) {
//		StringBuilder data = new StringBuilder();
//		data.append("[{ label: '" + subject + "', root:true, reason:0, url:'', children: [");
//
//		StringTokenizer st = new StringTokenizer(hashtag, ",");
//		while(st.hasMoreTokens()) {
//			data.append("{ label: '" + st.nextToken() + "', reason:0, },");
//		}
//		data.append("],},]");
//
//		return data.toString();
//	}

	@Override
	public NodeResultDto setNode(NodeDto dto) throws SQLException {
		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}
		
		Gson gson = new Gson();
		Object obj = dto.getNodeObject();
		dto.setNodeObject(gson.toJson(obj));

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

		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
			result.setResult("FIND_MIND_ID_ERROR");
			return result;
		}

		Object nodeObject = nodeMapper.getNode(dto);
		if(nodeObject==null) {
			result.setResult("GET_NODE_ERROR_NODE_IS_NULL");
			return result;
		}

		nodeDto.setNodeObject(nodeObject);
		result.setNodeDto(nodeDto);
		result.setResult("SUCCESS");
		return result;
	}

}

