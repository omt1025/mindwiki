package com.mindwiki.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ScrapDto;
import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.LikeDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.service.FollowerService;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.MindService;

@RestController
@RequestMapping("/mindwiki/follower")
public class FollowerController {

	@Autowired
	private MindService mindSvc;
	
	
	@Autowired
	private FollowerService followSvc;
	
	@Autowired
	private JwtService jwtSvc;
	
	

	
	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> follower_save(
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="followeremail", required=false) String followerEmail) throws UnsupportedEncodingException, SQLException {
		
		Map<String, Object> claimMap=jwtSvc.verifyJWT(jwt);
		  
		String myEmail=(String) claimMap.get("email");
		
		String followerName=followSvc.searchNameByEmail(followerEmail);
		System.out.println("이름 가져옴 "+ followerName);
		
		FollowerDto followInfo=new FollowerDto(myEmail,followerEmail,followerName);
		
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status=null;
		
		try {
			followSvc.save(followInfo);
			System.out.println("저장완료");
			resultMap.put("message","SAVE");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			resultMap.put("message","ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}//저장해줌
		
		

		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
	
	@GetMapping("/list")
	public ResponseEntity<List<FollowerDto>> follower_list(
			@RequestParam(value="jwt", required=false) String jwt) throws SQLException, UnsupportedEncodingException{
		
		Map<String, Object> claimMap=jwtSvc.verifyJWT(jwt);
		  
		String myEmail=(String) claimMap.get("email");
		
		
		return new ResponseEntity<List<FollowerDto>>(followSvc.list(myEmail),HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/list/detail")
	public ResponseEntity<List<MindDto>> follower_list_detail(
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="followeremail", required=false) String followerEmail) throws SQLException{
		
		mindSvc.readByEmail(followerEmail);
		
		return new ResponseEntity<List<MindDto>>(mindSvc.readByEmail(followerEmail), HttpStatus.OK);
	}
	
	
	
	
	
	@DeleteMapping("/list/delete")
	public ResponseEntity<Map<String, Object>> follower_list_delete(
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="followeremail", required=false) String followerEmail) throws UnsupportedEncodingException{
		
		Map<String, Object> claimMap=jwtSvc.verifyJWT(jwt);
		  
		String myEmail=(String) claimMap.get("email");
		
		String followerName=null;
		
		FollowerDto followInfo=new FollowerDto(myEmail,followerEmail,followerName);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status=null;
		
		try {
			followSvc.deleteByFollower(followInfo);
			System.out.println("삭제완료");
			resultMap.put("message","DELETE");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			
			resultMap.put("message","ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		

		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
	
}
