package com.mindwiki.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mindwiki.model.MemberDto;
import com.mindwiki.service.LoginService;


@CrossOrigin("*")
@RestController
@Controller
public class LoginController {

	@Autowired
	private LoginService loginSvc;
	 
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(HttpSession hs,
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="pass", required=false) String pw){
		
		System.out.println("일단 login controller");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		MemberDto temp_mem=new MemberDto();
		
		
	
		
		temp_mem.setIdentification(id);
		temp_mem.setPassword(pw);
		
		String svcResult;
		
		
		
		try {
			svcResult = loginSvc.login(temp_mem);
			
			if(svcResult.equals("OK")) {//로그인성공
				resultMap.put("message", "SUCCESS");
				status=HttpStatus.ACCEPTED;
			}else {//로그인 실패 비밀번호 실패
				resultMap.put("message", "FAIL");
				status=HttpStatus.ACCEPTED;
			}
			
		} catch (SQLException e) {//에러
				status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		
		
		System.out.println(new ResponseEntity<Map<String, Object>>(resultMap, status));

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
}
