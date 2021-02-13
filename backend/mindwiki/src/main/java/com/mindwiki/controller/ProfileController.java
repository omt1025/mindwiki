package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mindwiki.service.JwtService;
import com.mindwiki.service.JwtServiceImpl;
import com.mindwiki.service.ProfileService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private JwtService jwtService;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 회원 가입
	 * 최종 수정일: 2021.02.04.
	 *******************************************************************************/

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(HttpSession session,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="realName", required=false) String realName,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="nickName", required=false) String nickName,
			@RequestParam(value="hashtag", required=false) String hashtag){
		System.out.println("ProfileController] /profile/register ");
		System.out.println("email: " + email + ", password: " + password 
				+ ", realName: " + realName + ", nickName: " + nickName + ", hashtag: " + hashtag); 

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPassword(password);
		profileDto.setRealName(realName);
		profileDto.setNickName(nickName);
		profileDto.setHashtag(hashtag);

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			ProfileResultDto resultDto = profileService.register(profileDto);
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


	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 회원 탈퇴
	 * 최종 수정일: 2021.02.03.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	@PostMapping("/withdrawal")
	public ResponseEntity<Map<String, Object>> withdrawal(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="password", required=false) String password) throws UnsupportedEncodingException{
		System.out.println("ProfileController] /profile/withdrawal ");
		System.out.println("password: " + password); 

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setPassword(password);
		profileDto.setEmail(email);

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			ProfileResultDto serviceResult = profileService.withdrawal(profileDto);
			if(serviceResult.getResult()=="SUCCESS") {
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "FAIL");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
	}


	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 비밀번호 변경
	 * 최종 수정일: 2021.02.03.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	@PostMapping("/changePassword")
	public ResponseEntity<Map<String, Object>> changePassword(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="oldPassword", required=false) String oldPassword,
			@RequestParam(value="newPassword", required=false) String newPassword) throws UnsupportedEncodingException{
		System.out.println("ProfileController] /profile/changePassword ");
		System.out.println("oldPassword: " + oldPassword + ", newPassword:" + newPassword); 

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPassword(oldPassword);

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			ProfileResultDto serviceResult = profileService.changePassword(profileDto, newPassword);
			if(serviceResult.getResult()=="SUCCESS") {
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "FAIL");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
	}


	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 회원 정보 수정
	 * 최종 수정일: 2021.02.03.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	@PostMapping("/changeProfile")
	public ResponseEntity<Map<String, Object>> changeProfile(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber,
			@RequestParam(value="nickName", required=false) String nickName) throws UnsupportedEncodingException{
		System.out.println("ProfileController] /profile/changeProfile");
		System.out.println("phoneNumber: " + phoneNumber + ", nickName:" + nickName); 

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPhoneNumber(phoneNumber);
		profileDto.setNickName(nickName);

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			ProfileResultDto serviceResult = profileService.changeProfile(profileDto);
			if(serviceResult.getResult()=="SUCCESS") {
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "FAIL");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// TODO
	@PostMapping("/getProfile")
	public ResponseEntity<ProfileDto> getProfile(HttpSession session,
			@RequestParam(value="email", required=false) String email){
		System.out.println("ProfileController] /profile/getProfile");
		System.out.println("email: " + email); 

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);

		ProfileDto resultProfile = new ProfileDto();
		HttpStatus status = null;

		ProfileResultDto result = new ProfileResultDto();


		//		try {
		//			
		//			
		//			
		//			if(result.getResult()=="SUCCESS") {
		//				result.put("message", "SUCCESS");
		//				status = HttpStatus.ACCEPTED;
		//			}else {
		//				result.put("message", "FAIL");
		//				status = HttpStatus.INTERNAL_SERVER_ERROR;
		//			}
		//		}catch(SQLException e) {
		//			status = HttpStatus.INTERNAL_SERVER_ERROR;
		//			e.printStackTrace();
		//		}
		//
		//		System.out.println(" status: " + status.toString());
		//		return new ResponseEntity<ProfileDto>(returnProfile, status);
		return null;
	}

	/******************************************************************************
	 * 작성자 : 
	 * 기능 : 해당 계정이 존재하는지 확인
	 * 최종 수정일: 2021.02.08.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	// TODO
	@PostMapping("/isExist")
	public ResponseEntity<Map<String, Object>> isExist(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException{
		System.out.println("ProfileController] /profile/isExist");
		//		System.out.println("jwt: " + jwt); 

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;

		try {
			ProfileResultDto serviceResult = profileService.isExist(profileDto);
			if(serviceResult.getResult()=="EXIST") {
				result.put("message", "EXIST");
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message", "NOT EXIST");
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

	// TODO
	@PostMapping("/memberList")
	public ResponseEntity<List<ProfileDto>> memberList(@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException, SQLException{
		System.out.println("ProfileController] /profile/memberList");

		HttpStatus status=null;

		try {
			profileService.memberList();
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<List<ProfileDto>> (profileService.memberList(), HttpStatus.OK);
	}

}

