/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : liketag
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.service.FollowerService;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.LiketagService;
import com.mindwiki.service.MindService;

@RestController
@RequestMapping("/mindwiki/liketag")
public class LikeTagController {

	@Autowired
	private MindService mindSvc;

	@Autowired
	private FollowerService followSvc;

	@Autowired
	private LiketagService LiketagSvc;

	@Autowired
	private JwtService jwtSvc;

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> list(@RequestParam(value = "jwt", required = false) String jwt)
			throws SQLException, UnsupportedEncodingException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String email = (String) claimMap.get("email");

		System.out.println(LiketagSvc.list(email));

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("hashtag", LiketagSvc.list(email));
		// 그냥 responseentity<string>으로 리턴시 오류남 맵으로 넣어서 보내줘야함 한글때문에

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/home/list")
	public ResponseEntity<List<MindDto>> home_list(@RequestParam(value = "jwt", required = false) String jwt)
			throws SQLException, UnsupportedEncodingException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String email = (String) claimMap.get("email");

		// System.out.println(LiketagSvc.list(email));

		StringTokenizer st = new StringTokenizer(LiketagSvc.list(email), "#");

		int endNum = st.countTokens();
		Set<Integer> homeTagListID = new HashSet<Integer>();// 중복없게 id만 저장
		List<MindDto> homeTagList = new ArrayList<>();

		int prev_mindID = 0;
		MindDto mind = new MindDto();

		for (int i = 0; i < endNum; i++) {

			List<MindDto> innerMind = LiketagSvc.likeTagAndMind(st.nextToken());

			for (int j = 0; j < innerMind.size(); j++) {// 리스트 안에 리스트를 잠시 생성해서 큰리스트로 쪼개서 내보내줌
				mind = innerMind.get(j);
				if (mind != null) {
					if (prev_mindID == mind.getMindID()) {

					} else {
						homeTagListID.add(mind.getMindID());
						prev_mindID = mind.getMindID();
					}
				}

			}

		}

		Object[] array = homeTagListID.toArray();

		// 이제 mindID개수만큼 출력값 설정
		for (int i = 0; i < homeTagListID.size(); i++) {
			homeTagList.add(mindSvc.readByMindID((int) array[i]));// int는 셋으로 중복값을 제거하기위해 사용
		}

		return new ResponseEntity<List<MindDto>>(homeTagList, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> update(@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "hashtag", required = false) String hashtag)
			throws SQLException, UnsupportedEncodingException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String email = (String) claimMap.get("email");

		LiketagSvc.update(hashtag, email);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("hashtag", LiketagSvc.list(email));
		// 그냥 responseentity<string>으로 리턴시 오류남 맵으로 넣어서 보내줘야함 한글때문에

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

}
