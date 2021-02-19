/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : login
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.LoginService;

@RestController
@RequestMapping("/mindwiki")
public class LoginController {

	@Autowired
	private LoginService loginSvc;

	@Autowired
	private JwtService jwtSvc;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(HttpSession hs,
			@Email @RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "pass", required = false) String pw) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		ProfileDto temp_mem = new ProfileDto();

		if (pw.length() > 12) {
			pw = pw.substring(0, 12);
		}

		temp_mem.setEmail(id);
		temp_mem.setPassword(pw);

		ProfileDto memberDto = new ProfileDto();

		memberDto = loginSvc.login(temp_mem);

		// 이메일이 있을때
		if (memberDto.getNickName() != null) {// 로그인성공
			System.out.println(memberDto.getNickName());

			String jwt = jwtSvc.createToken("userInfo", memberDto.getEmail(), memberDto.getNickName());
			resultMap.put("message", "SUCCESS");
			resultMap.put("jwt", jwt);

			status = HttpStatus.ACCEPTED;
		} else {// 로그인 실패 비밀번호 실패
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// jwt를 요청하면 추가정보를 얻을 수 있음
	@PostMapping("/jwtCheck")
	public Map<String, Object> sessionCheck(HttpSession hs, @RequestParam(value = "jwt", required = false) String jwt) {
		Map<String, Object> claims = new HashMap<>();

		try {

			claims = jwtSvc.verifyJWT(jwt);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return claims;
	}

}
