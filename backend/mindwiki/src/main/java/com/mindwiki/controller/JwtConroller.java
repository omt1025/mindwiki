//package com.mindwiki.controller;
//
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.mindwiki.model.OauthDto;
//
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/jwt")
//public class JwtConroller {
//	//로그인을 하고 메인페이지로 리턴이 되면 리다이렉트 된 후이니까 created axios로 값을 확인하고, 서버쪽에서는 
//	//받는 메서드를 하나 만들어서 아니면 intercepter로 해도되고
//	//받는 메서드로 확인하고 jwt를 발급해준다음에 세션을 끊어줘도 되고 아예 flag 01로나눠서 1이면 토큰을 주고 0으로 만들어도된다.
//	//알고리즘으로는 1.내가 jwt가 없는 상태에서, 요청을 보냈는데 로그인 세션이 존재하면 발급
//	//2.내가 jwt가 있는 상태에서 요청을 보내면 확인해주는것, 로그인되었나.
//	//3.전제조건은 항상 created에서 jwt를 보낸다는것
//	//리다이렉트시 값을 전달하면서 created로 받아서 세션이 존재하나 확인할 수 있도록함
//	
//	
//	
//	@PostMapping("/jwtForOAuth")
//	public String getUserInfo() {
//		OauthDto oauth;
//		if(oauth.getCheck()==1) {
//			
//			
//		}
//	}
//	
//	
//}
