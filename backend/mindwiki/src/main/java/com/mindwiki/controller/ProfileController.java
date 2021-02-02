package com.mindwiki.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;
import com.mindwiki.service.ProfileService;

@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	// TODO
	// JWT, RequestBody
	// auto increment 확인
	// return
	@PostMapping("/register")
	public ResponseEntity<ProfileResultDto> register(HttpSession session,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="realName", required=false) String realName,
			@RequestParam(value="hashtag", required=false) String hashtag){
		System.out.println("ProfileController] /profile/register ");
		System.out.println("email: " + email + ", password: " + password 
				+ ", realName: " + realName + ", hashtag: " + hashtag);
		
		ProfileDto profileDto = new ProfileDto(email, password, realName, hashtag);
		
		HttpStatus status = null;
		ProfileResultDto result = new ProfileResultDto();
		
		try {
			result = profileService.register(profileDto);
			if(result.getResult()==SUCCESS) {
				status = HttpStatus.ACCEPTED;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		System.out.println(" status: " + status.toString());
		return new ResponseEntity<ProfileResultDto>(result, status);
	}
	
	// TODO
	// JWT, RequestBody
	// return
	@PostMapping("/withdrawal")
	public ResponseEntity<ProfileResultDto> withdrawal(HttpSession session,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password){
		System.out.println("ProfileController] /profile/withdrawal ");
		System.out.println("email: " + email + ", password: " + password); 
		
		ProfileDto profileDto = new ProfileDto(email, password);
		
		HttpStatus status = null;
		ProfileResultDto result = new ProfileResultDto();
		
		try {
			result = profileService.withdrawal(profileDto);
			if(result.getResult()==SUCCESS) {
				status = HttpStatus.ACCEPTED;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		System.out.println(" status: " + status.toString());
		return new ResponseEntity<ProfileResultDto>(result, status);
	}
	
	// TODO
	// JWT
	// return
	@PostMapping("/changePassword")
	public ResponseEntity<ProfileResultDto> changePassword(HttpSession session,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="oldPassword", required=false) String oldPassword,
			@RequestParam(value="newPassword", required=false) String newPassword){
		System.out.println("ProfileController] /profile/changePassword ");
		System.out.println("email: " + email + ", oldPassword: " + oldPassword + ", newPassword:" + newPassword); 
		
		ProfileDto profileDto = new ProfileDto(email, oldPassword);
		
		HttpStatus status = null;
		ProfileResultDto result = new ProfileResultDto();
		
		try {
			result = profileService.changePassword(profileDto, newPassword);
			if(result.getResult()==SUCCESS) {
				status = HttpStatus.ACCEPTED;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
	
		System.out.println(" status: " + status.toString());
		return new ResponseEntity<ProfileResultDto>(result, status);
	}
	
	// TODO
	// JWT, RequestBody
	// return
	@PostMapping("/changeProfile")
	public ResponseEntity<ProfileResultDto> changeProfile(HttpSession session,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber,
			@RequestParam(value="nickName", required=false) String nickName){
		System.out.println("ProfileController] /profile/changeProfile");
		System.out.println("email: " + email + ", phoneNumber: " + phoneNumber + ", nickName:" + nickName); 
		
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPhoneNumber(phoneNumber);
		profileDto.setNickName(nickName);
		
		HttpStatus status = null;
		ProfileResultDto result = new ProfileResultDto();
		
		try {
			result = profileService.changeProfile(profileDto);
			if(result.getResult()==SUCCESS) {
				status = HttpStatus.ACCEPTED;
			}else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		System.out.println(" status: " + status.toString());
		return new ResponseEntity<ProfileResultDto>(result, status);
	}
	

}
