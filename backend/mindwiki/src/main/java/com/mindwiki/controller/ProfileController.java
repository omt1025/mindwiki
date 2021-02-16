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

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPassword(password);
		profileDto.setRealName(realName);
		profileDto.setNickName(nickName);
		profileDto.setHashtag(hashtag);
		
		return processRegister(profileDto);
	}
	
	private ResponseEntity<Map<String, Object>> processRegister(ProfileDto dto){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status;
		
		try {
			ProfileResultDto resultDto = profileService.register(dto);
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

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setPassword(password);
		profileDto.setEmail(email);

		return processWithdrawal(profileDto);
	}
	
	private ResponseEntity<Map<String, Object>> processWithdrawal(ProfileDto dto){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status;
		
		try {
			ProfileResultDto serviceResult = profileService.withdrawal(dto);
			if(serviceResult.getResult()=="SUCCESS") {
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
			@RequestParam(value="oldPassword", required=false) String oldPW,
			@RequestParam(value="newPassword", required=false) String newPW) throws UnsupportedEncodingException{

		Map<String, Object> jwtData =  jwtService.verifyJWT(jwt);
		String email = (String)jwtData.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPassword(oldPW);
		
		return processChangePW(profileDto, newPW);
	}
	
	private ResponseEntity<Map<String, Object>> processChangePW(ProfileDto dto,  String newPW){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status;
		
		try {
			ProfileResultDto serviceResult = profileService.changePassword(dto, newPW);
			if(serviceResult.getResult()=="SUCCESS") {
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
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	/******************************************************************************
	 * 작성자 : 서울 2반 4팀 김정웅
	 * 기능 : 비밀번호 찾기
	 * 최종 수정일: 2021.02.03.
	 *******************************************************************************/
	
	@PostMapping("/sendTempPassword")
	public ResponseEntity<Map<String, Object>> sendTempPassword(HttpSession session,
			@RequestParam(value="email", required=false) String email){
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		
		return processSendTempPassword(profileDto);
	}
	
	private ResponseEntity<Map<String, Object>> processSendTempPassword(ProfileDto dto){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status;
		
		try {
			ProfileResultDto serviceResult = profileService.sendTempPassword(dto);
			if(serviceResult.getResult()=="SUCCESS") {
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
			@RequestParam(value = "files", required = false) MultipartFile file,
			@RequestParam(value="phoneNumber", required=false) String phoneNumber,
			@RequestParam(value="nickName", required=false) String nickName) throws UnsupportedEncodingException{

		Map<String, Object> claimMap =  jwtService.verifyJWT(jwt);
		String email = (String)claimMap.get("email");

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);
		profileDto.setPhoneNumber(phoneNumber);
		profileDto.setNickName(nickName);

		return processChangeProfile(profileDto, file);
	}
	
	private ResponseEntity<Map<String, Object>> processChangeProfile(ProfileDto dto, MultipartFile file){
		Map<String, Object> result = new HashMap<>();
		HttpStatus status;
		
		try {
			ProfileResultDto serviceResult = profileService.changeProfile(dto, file);
			if(serviceResult.getResult()=="SUCCESS") {
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
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	/******************************************************************************
	 * 작성자 : 
	 * 기능 : 해당 계정이 존재하는지 확인
	 * 최종 수정일: 2021.02.08.
	 * @throws UnsupportedEncodingException 
	 *******************************************************************************/

	// TODO
	@PostMapping("/getProfile")
	public ResponseEntity<ProfileDto> getProfile(HttpSession session,
			@RequestParam(value="email", required=false) String email){

		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(email);

		ProfileDto resultProfile = new ProfileDto();
		HttpStatus status = null;

		ProfileResultDto result = new ProfileResultDto();

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
	 * 기능 : 해당 계정이 존재하는지 확인
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

