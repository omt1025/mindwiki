package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.service.JwtService;
import com.mindwiki.service.SearchService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;

	@Autowired
	private JwtService jwtService;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@PostMapping("/title")
	public ResponseEntity<Map<String, Object>> title(HttpSession session){
		System.out.println("SearchController] /search/title ");

//		Map<String, Object> result = new HashMap<>();
//		HttpStatus status = null;
//
//		try {
//			ProfileResultDto resultDto = searchService.register(profileDto);
//			if(resultDto.getResult()=="SUCCESS") {
//				result.put("message", "SUCCESS");
//				status = HttpStatus.ACCEPTED;
//			}else {
//				result.put("message", "FAIL");
//				status = HttpStatus.ACCEPTED;
//			}
//		}catch(SQLException e) {
//			result.put("message", "SERVER_ERROR");
//			status = HttpStatus.ACCEPTED;
//			e.printStackTrace();
//		}
//
//		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
//		return new ResponseEntity<Map<String, Object>>(result, status);
		return null;
	}
	
//	@PostMapping("/hashtag")
//	public ResponseEntity<List<ProfileDto>> memberList(@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException, SQLException{
//		System.out.println("ProfileController] /profile/memberList");
//
//		HttpStatus status=null;
//
//		try {
//			profileService.memberList();
//			status = HttpStatus.OK;
//		} catch (SQLException e) {
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			e.printStackTrace();
//		}
//
//		return new ResponseEntity<List<ProfileDto>> (profileService.memberList(), HttpStatus.OK);
//	}
	
	
	
	

}


