package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.MindService;
import com.mindwiki.service.NodeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/node")
public class NodeController {

	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private MindService mindService;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/getInitNode")
	public ResponseEntity<Object> getInitNode(HttpSession session,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "hashtag", required = false) String hashtag,
			@RequestParam(value = "subject", required = false) String subject) throws UnsupportedEncodingException, SQLException{
		/*
		String admin = (String) jwtService.verifyJWT(jwt).get("email");
		int MindID = mindService.getMindID(admin);
		nodeService.initNode(MindID, subject, hashtag);
		System.out.println("end of set initNode");
		*/
		
		StringTokenizer st = new StringTokenizer(hashtag, ",");
		String[] hashtagArr = new String[3];
		int idx = 0;
		while(st.hasMoreTokens()) {
			hashtagArr[idx] = st.nextToken();
		}
		
		return new ResponseEntity<Object>(	
				"[\r\n" + 
						"{\"children\":\r\n" + 
						"[{\"reason\":\"0\",\"label\":\""+hashtagArr[0] + "\"},\r\n" + 
						"{\"reason\":\"0\",\"label\":\""+ hashtagArr[1] + "\"},\r\n" + 
						"{\"reason\":\"0\",\"label\":\""+ hashtagArr[2] +"\"}],\r\n" + 
						"\"root\":\"true\",\r\n" + 
						"\"label\":\"" + subject + "\",\r\n" + 
						"\"url\":\"\"}\r\n" + 
						"]", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/setNode")
	public ResponseEntity<Map<String, Object>> setNode(HttpSession session,
			@RequestParam(value="MindID", required=false) int MindID,
			@RequestParam(value="data", required=false) Object nodeData) throws SQLException{
		Map<String, Object> reponse = new HashMap<>();
		
		Gson gson = new Gson();

		NodeDto dto = new NodeDto();
		dto.setMindID(MindID);
		String nodeString = gson.toJson(nodeData);
		dto.setNodeString(nodeString);

		NodeResultDto serviceResult = nodeService.setNode(dto);

		reponse.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(reponse, HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/getNode")
	public ResponseEntity<Object> getNode(HttpSession session,
			@RequestParam(value="MindID", required=false) int MindID) throws SQLException{
		
		NodeDto nodeDto = new NodeDto();
		nodeDto.setMindID(MindID);
		
<<<<<<< HEAD
		Object obj = nodeService.getNode(nodeDto);
		Gson gson = new Gson();
		return new ResponseEntity<Object>(gson.toJson(obj), HttpStatus.ACCEPTED);
	}

//		Object nodeObject = "";
//
//		try {
//			NodeResultDto serviceResult = nodeService.getNode(nodeDto);
//			if(serviceResult.getResult()=="SUCCESS") {
//				nodeObject = serviceResult.getNodeDto().getNodeObject();
//			}else {
//				System.out.println("/getNode service error");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<Object>(	
//		"[\r\n" + 
//		"{\"children\":\r\n" + 
//		"[{\"reason\":\"0\",\"" + "이렇게" + "\":\"0217_1643\"},\r\n" + 
//		"{\"reason\":\"0\",\"" +  "하면" + "\":\"0217_1643\"},\r\n" + 
//		"{\"reason\":\"0\",\"" +  "되나요" + "\":\"0217_1643\"}],\r\n" + 
//		"\"root\":\"true\",\r\n" + 
//		"\"label\":\"" + "신기하네요" + "\",\r\n" + 
//		"\"url\":\"\"}\r\n" + 
//		"]", HttpStatus.ACCEPTED);
////		return new ResponseEntity<Object>(gson.toJson(nodeObject), HttpStatus.ACCEPTED);
//	}
=======
		
		return new ResponseEntity<Object>(	
		"[\r\n" + 
		"{\"children\":\r\n" + 
		"[{\"reason\":\"0\",\"" + "label" + "\":\""+"dfefeaf"+"\"},\r\n" + 
		"{\"reason\":\"0\",\"" +  "label" + "\":\""+"dsfewfaewfawefdf"+"\"},\r\n" + 
		"{\"reason\":\"0\",\"" +  "label" + "\":\""+"sdfwefwefwfewfsdf"+"\"}],\r\n" + 
		"\"root\":\"true\",\r\n" + 
		"\"label\":\"" + "신기하네요" + "\",\r\n" + 
		"\"url\":\"\"}\r\n" + 
		"]", HttpStatus.ACCEPTED);
//		return new ResponseEntity<Object>(gson.toJson(nodeObject), HttpStatus.ACCEPTED);
	}

>>>>>>> 817d0018e5d2be0dabb5085ea6efefb13c228289

}
