package com.mindwiki.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.model.MemberDto;

@CrossOrigin("*")
@RestController
@Controller
public class MindController {

	
	
	@PostMapping("/mind")
	public ResponseEntity<Map<String, Object>> login(HttpSession hs,
			@RequestParam(value="mindName", required=false) String mindName,
			@RequestParam(value="tagNames", required=false) String tagNames){
		
		StringTokenizer st = new StringTokenizer(tagNames,"#");
		
		
		System.out.println("일단 mind controller");
		System.out.println(mindName);

		
		for(int i=0;i<3;i++) {
			System.out.print("#"+st.nextToken()+" ");
		}

		
		
		
		
		return null;
	}
	
}
