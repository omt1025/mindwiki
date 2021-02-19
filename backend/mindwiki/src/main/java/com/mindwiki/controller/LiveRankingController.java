/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : liveRanking
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.model.FollowerDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.service.FollowerService;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.LiveRankingService;
import com.mindwiki.service.MindService;

@RestController
@RequestMapping("/mindwiki/liveRanking")
public class LiveRankingController {

	@Autowired
	private LiveRankingService liveRankingSvc;

	@Autowired
	private JwtService jwtSvc;

	@GetMapping("/list")
	public ResponseEntity<List<MindDto>> follower_list(@RequestParam(value = "jwt", required = false) String jwt)
			throws SQLException, UnsupportedEncodingException {

		return new ResponseEntity<List<MindDto>>(liveRankingSvc.liveRankingList(), HttpStatus.OK);
	}

}
