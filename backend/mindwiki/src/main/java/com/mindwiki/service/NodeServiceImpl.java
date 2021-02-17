package com.mindwiki.service;

import java.sql.SQLException;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mindwiki.dao.NodeDao;
import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;

@Service
public class NodeServiceImpl implements NodeService{

	@Autowired
	private SqlSession session;

	private static final int SUCCESS = 1;

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

		if(nodeMapper.initNode(dto)!=SUCCESS) {
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
				"[{\"reason\":\"0\",\"label\":\""+hashtagArr[0] + "\"},\r\n" + 
				"{\"reason\":\"0\",\"label\":\""+ hashtagArr[1] + "\"},\r\n" + 
				"{\"reason\":\"0\",\"label\":\""+ hashtagArr[2] +"\"}],\r\n" + 
				"\"root\":\"true\",\r\n" + 
				"\"label\":\"" + subject + "\",\r\n" + 
				"\"url\":\"\"}\r\n" + 
				"]";
		
		return obj;
	}

	@Override
	public void setNode(Object nodeObject, int MindID) throws SQLException {
		
		NodeDao nodeMapper = session.getMapper(NodeDao.class);
//		
//		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
//			result.setResult("FIND_MIND_ID_ERROR");
//			return result;
//		}
//		
		Gson gson = new Gson();
//		Object obj = dto.getNodeObject();
//		dto.setNodeObject(gson.toJson(obj));
//
//		if(nodeMapper.setNode(dto)!=SUCCESS) {
//			result.setResult("SET_NODES_ERROR");
//			return result;
//		}
		
		nodeMapper.setNode(nodeObject, MindID);
//
//		result.setResult("SUCCESS");
//		return result;
	}

	@Override
	public Object getNode(int MindID) throws SQLException {
//		NodeDto nodeDto = new NodeDto();
//		NodeResultDto result = new NodeResultDto();
		NodeDao nodeMapper = session.getMapper(NodeDao.class);
//
//		if(nodeMapper.existByMindID(dto)!=SUCCESS) {
//			result.setResult("FIND_MIND_ID_ERROR");
//			return result;
//		}
//
//		Object nodeObject = nodeMapper.getNode(dto);
//		if(nodeObject==null) {
//			result.setResult("GET_NODE_ERROR_NODE_IS_NULL");
//			return result;
//		}
//
//		nodeDto.setNodeObject(nodeObject);
//		result.setNodeDto(nodeDto);
//		result.setResult("SUCCESS");
//		return result;
		
		return nodeMapper.getNode(MindID);
	}

	@Override
	public NodeResultDto setNodeObject(NodeDto dto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

