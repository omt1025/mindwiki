package com.mindwiki.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
import com.mindwiki.service.NodeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/node")
public class NodeController {

	@Autowired
	private NodeService nodeService;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@PostMapping("/setNode")
	public ResponseEntity<Map<String, Object>> setNode(HttpSession session,
			@RequestParam(value="MindID", required=false) int MindID,
			@RequestParam(value="data", required=false) Object nodeData){
		System.out.print("NodeController] /setNode/ ");
		System.out.println("MindID: " + MindID + " data:" + nodeData);
		
		Gson gson = new Gson();
		String nodeString = gson.toJson(nodeData);

		NodeDto nodeDto = new NodeDto();
		nodeDto.setMindID(MindID);
		nodeDto.setNodeString(nodeString);

		return processSetNode(nodeDto);
	}

	private ResponseEntity<Map<String, Object>> processSetNode(NodeDto dto){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			NodeResultDto resultDto = nodeService.setNode(dto);

			if(resultDto.getResult()=="SUCCESS") {
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "FAIL");
				status = HttpStatus.ACCEPTED;
			}
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@PostMapping("/getNode")
	public ResponseEntity<Map<String, Object>> getNode(HttpSession session,
			@RequestParam(value="MindID", required=false) int MindID){
		System.out.println("NodeController] /getNode/ ");
		System.out.println("MindID: " + MindID);

		NodeDto nodeDto = new NodeDto();
		nodeDto.setMindID(MindID);

		return processGetNode(nodeDto);
	}

	private ResponseEntity<Map<String, Object>> processGetNode(NodeDto dto){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			NodeResultDto serviceResult = nodeService.getNode(dto);
			System.out.println("processGetNode result:");
			System.out.println(serviceResult.getResult());
			

			if(serviceResult.getResult()=="SUCCESS") {
				result.put("message", "SUCCESS");
				
				Gson gson = new Gson();
				String nodeString = serviceResult.getNodeDto().getNodeString();
				
				System.out.println(nodeString);
				
				result.put("data", gson.toJson(nodeString));
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "FAIL");
				status = HttpStatus.ACCEPTED;
			}
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

}
