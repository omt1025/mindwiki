/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : follower
* 최종 수정일: 2021.02.04.
*******************************************************************************/
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

	// Create Read Delete

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> follower_save(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws UnsupportedEncodingException, SQLException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String myEmail = (String) claimMap.get("email");

		String followerName = followSvc.searchNameByEmail(followerEmail);
		System.out.println("이름 가져옴 " + followerName);

		FollowerDto followInfo = new FollowerDto(myEmail, followerEmail, followerName);

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			followSvc.save(followInfo);
			System.out.println("저장완료");
			resultMap.put("message", "SAVE");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			resultMap.put("message", "ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@GetMapping("/list")
	public ResponseEntity<List<FollowerDto>> following_list(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws SQLException, UnsupportedEncodingException {

		String myEmail;
		if (followerEmail == null) {

			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

			myEmail = (String) claimMap.get("email");
		} else {
			myEmail = followerEmail;
		}

		return new ResponseEntity<List<FollowerDto>>(followSvc.list(myEmail), HttpStatus.OK);
	}


	@GetMapping("/list/profilepic")
	public ResponseEntity<List<String>> list_pic(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws SQLException, UnsupportedEncodingException {

		String myEmail;
		if (followerEmail == null) {

			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

			myEmail = (String) claimMap.get("email");
		} else {
			myEmail = followerEmail;
		}

		List<FollowerDto> a = followSvc.list(myEmail);
		List<String> b = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {
			String email = a.get(i).getFollowerEmail();
			b.add(followSvc.getProfilePic(email));
		}

		return new ResponseEntity<List<String>>(b, HttpStatus.OK);
	}


	@GetMapping("/followerlist")
	public ResponseEntity<List<String>> follower_list(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws SQLException, UnsupportedEncodingException {

		String myEmail;
		if (followerEmail == null) {

			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

			myEmail = (String) claimMap.get("email");
		} else {
			myEmail = followerEmail;
		}

		return new ResponseEntity<List<String>>(followSvc.followerList(myEmail), HttpStatus.OK);
	}


	@GetMapping("/followerlist/profilepic")
	public ResponseEntity<List<String>> follower_list_pic(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws SQLException, UnsupportedEncodingException {

		String myEmail;
		if (followerEmail == null) {

			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

			myEmail = (String) claimMap.get("email");
		} else {
			myEmail = followerEmail;
		}

		List<String> a = followSvc.followerList(myEmail);
		List<String> b = new ArrayList<>();// 결과 내보내줌 이메일로 검색해서

		for (int i = 0; i < a.size(); i++) {
			String email = a.get(i);
			b.add(followSvc.getProfilePic(email));
		}

		return new ResponseEntity<List<String>>(b, HttpStatus.OK);
	}

	@GetMapping("/list/detail")
	public ResponseEntity<List<MindDto>> follower_list_detail(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail) throws SQLException {

		mindSvc.readByEmail(followerEmail);

		return new ResponseEntity<List<MindDto>>(mindSvc.readByEmail(followerEmail), HttpStatus.OK);
	}

	@DeleteMapping("/list/delete")
	public ResponseEntity<Map<String, Object>> follower_list_delete(
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "followeremail", required = false) String followerEmail)
			throws UnsupportedEncodingException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String myEmail = (String) claimMap.get("email");

		String followerName = null;

		FollowerDto followInfo = new FollowerDto(myEmail, followerEmail, followerName);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			followSvc.deleteByFollower(followInfo);
			resultMap.put("message", "DELETE");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			resultMap.put("message", "ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
