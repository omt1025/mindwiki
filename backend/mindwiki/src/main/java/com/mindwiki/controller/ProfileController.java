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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.ProfileService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private JwtService jwtService;

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

		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;
		
		ProfileDto dto = new ProfileDto();
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setRealName(realName);
		dto.setNickName(nickName);
		dto.setHashtag(hashtag);
		
		System.out.println(dto);
		
		try {
			serviceResult = profileService.register(dto);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
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

		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;
		
		ProfileDto dto = new ProfileDto();
		dto.setPassword(password);
		String email = (String)jwtService.verifyJWT(jwt).get("email");
		dto.setEmail(email);
		
		try {
			serviceResult = profileService.withdrawal(dto);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
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
			@RequestParam(value="oldPassword", required=false) String oldPW,
			@RequestParam(value="newPassword", required=false) String newPW) throws UnsupportedEncodingException{
		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;

		ProfileDto dto = new ProfileDto();
		dto.setPassword(oldPW);
		String email = (String)jwtService.verifyJWT(jwt).get("email");
		dto.setEmail(email);
		
		try {
			serviceResult = profileService.changePassword(dto, newPW);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 비밀번호 찾기
	 * 최종 수정일: 2021.02.03.
	 *******************************************************************************/
	
	@PostMapping("/sendTempPassword")
	public ResponseEntity<Map<String, Object>> sendTempPassword(HttpSession session,
			@RequestParam(value="email", required=false) String email){
		ProfileDto dto = new ProfileDto();
		dto.setEmail(email);
		
		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;
		
		try {
			serviceResult = profileService.sendTempPassword(dto);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
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
			@RequestParam(value = "files", required = false) MultipartFile file,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber,
			@RequestParam(value="nickName", required=false) String nickName) throws UnsupportedEncodingException{

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto dto = new ProfileDto();
		dto.setEmail(email);
		dto.setPhoneNumber(phoneNumber);
		dto.setNickName(nickName);

		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;
		
		try {
			serviceResult = profileService.changeProfile(dto, file);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	/******************************************************************************
	 * 작성자 : 
	 * 기능 : 내 프로필 정보 리턴
	 * 최종 수정일: 2021.02..
	 * @throws Exception 
	 *******************************************************************************/

	// TODO
	@PostMapping("/getMyProfile")
	public ResponseEntity<Map<String, Object>> getMyProfile(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt) throws Exception{

		String email = (String)jwtService.verifyJWT(jwt).get("email");

		Map<String, Object> response = new HashMap<>();
		ProfileResultDto serviceResult = null;
		try {
			serviceResult = profileService.getMyProfile(email);
		}catch(SQLException e) {
			response.put("exception", "SQLException");
			e.printStackTrace();
		}
		
//		response.put("info", profileService.getMyProfile(email));
		response.put("info", serviceResult.getProfileDto());
		response.put("message", serviceResult.getResult());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	/******************************************************************************
	 * 작성자 : 
	 * 기능 : 상대방 프로필 정보 리턴
	 * 최종 수정일: 2021.02..
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	// TODO
	@PostMapping("/getProfile")
	public ResponseEntity<Map<String, Object>> getProfile(HttpSession session,
			@RequestParam(value="email", required=false) String email) throws Exception{

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			result.put("info", profileService.getMyProfile(email));
			result.put("message", "SUCCESS");
			
		}catch(SQLException e) {
			result.put("message", "SERVER_ERROR");
			e.printStackTrace();
		}

		System.out.println(new ResponseEntity<Map<String, Object>>(result, status));
		return new ResponseEntity<Map<String, Object>>(result, status);
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

	/******************************************************************************
	 * 작성자 : 
	 * 기능 : 회원목록 리턴
	 * 최종 수정일: 2021.02.08.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/
	
	@PostMapping("/memberList")
	public ResponseEntity<List<ProfileDto>> memberList(HttpSession session,
			@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException, SQLException{
		List<ProfileDto> allProfile = null;
		HttpStatus status;
		
		try {
			allProfile = profileService.getAllProfile();
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<ProfileDto>> (allProfile, status);
	}
	
	@GetMapping("/profilepicture")
	public ResponseEntity<Map<String,Object>> profilePicture(
			@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException{
		
		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");
		
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = null;
		
		String picUrl = null;
		try {
			picUrl = profileService.getProfilePic(email);
			result.put("picture", picUrl);
			result.put("message", "SUCCESS");
			status = HttpStatus.ACCEPTED;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.put("message", "ERROR");
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>> (result, status);
	}

}

