/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 댓글
* 최종 수정일: 2021.02.04.
*******************************************************************************/
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindwiki.model.CommentDto;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.service.CommentService;
import com.mindwiki.service.JwtService;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;

@Api("CommentController V1")
@RestController
@RequestMapping("/mindwiki")
public class CommentController {

	@Autowired
	private JwtService jwtSvc;

	@Autowired
	private CommentService commentSvc;

	@ApiOperation(value = "댓글 작성", notes = "댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라  map의 message에 'comment가 등록되었습니다.' 또는 'comment 등록실패(회원가입정보가 없습니다.)", response = Map.class)
	@PostMapping("/mind/comment/make/{no}")
	public ResponseEntity<Map<String, Object>> make(HttpSession hs,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "data", required = false) String data, @PathVariable int no)
			throws UnsupportedEncodingException {

		Map<String, Object> claims = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		int MindID = no;

		HttpStatus status = null;

		try {
			claims = jwtSvc.verifyJWT(jwt);
			String email = (String) claims.get("email");
			CommentDto comment = new CommentDto(MindID, data, email);
			commentSvc.make(comment);// comment 생성
			resultMap.put("message", "SUCCESS");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		System.out.println("일단 comment controller");

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// Comment read 해당되는 MindIDno에 있는 모든 것을 불러온다.
	@ApiOperation(value = "해당마인드 댓글 전부 불러오기", notes = "댓글 정보를 읽어온다. List로 받아온다.", response = List.class)
	@GetMapping("/mind/comment/read/{no}")
	public ResponseEntity<List<CommentDto>> read(@PathVariable int no) throws SQLException {

		// 1번 mymind 불러오기
		// 2번
		int MindID = no;

		// 마인드 내부에 들어오면 이 페이지의 mindID가 무엇인지 알려줘야함

		System.out.println(commentSvc.read(MindID));

		return new ResponseEntity<List<CommentDto>>(commentSvc.read(MindID), HttpStatus.OK);

	}

	// mind update
	@ApiOperation(value = "댓글 한개를 수정한다.", notes = "댓글 하나를 수정한다. 수정결과에 따라 map에 message 성공시 : 댓글이 수정되었습니다. 실패시:댓글 수정실패(로그인해주세요.) Map으로 반환된다. ", response = Map.class)
	@PutMapping("/mind/comment/update/{no}")
	public ResponseEntity<Map<String, Object>> update(@RequestParam(value = "jwt", required = false) String jwt,
			@PathVariable int no, @RequestParam(value = "commentID", required = false) int commentID,
			@RequestParam(value = "data", required = false) String data) throws UnsupportedEncodingException {

		Map<String, Object> claims = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		int MindID = no;

		claims = jwtSvc.verifyJWT(jwt);
		String email = (String) claims.get("email");

		System.out.println(MindID);
		CommentDto comment = new CommentDto(MindID, commentID, data, email);
		HttpStatus status = null;

		try {
			if (email != null) {// 여기 수정해야됨 세션이나 관리자로 권한확인
				commentSvc.updateByCommentID(comment);
				resultMap.put("message", "UPDATE SUCCESS");
				System.out.println("수정됨");
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "FAIL(LOGIN)");
				status = HttpStatus.OK;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			// returnMessage="마인드 등록 실패!";
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// mind delete
	@ApiOperation(value = "댓글 한개를 삭제한다.", notes = "댓글 하나를 삭제한다. 수정결과에 따라 map에 message 성공시 : 정상삭제완료. 실패시: 서버오류삭제실패 ", response = Map.class)
	@DeleteMapping("/mind/comment/delete/{no}")
	public ResponseEntity<Map<String, Object>> delete(HttpSession hs, @PathVariable int no,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "commentID", required = false) int commentID,
			@RequestParam(value = "data", required = false) String data) throws UnsupportedEncodingException {

		Map<String, Object> claims = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		int MindID = no;

		claims = jwtSvc.verifyJWT(jwt);
		String email = (String) claims.get("email");
		HttpStatus status = null;

		// 1번 mymind 불러오기
		// 2번
		CommentDto comment = new CommentDto(MindID, commentID, data, email);
		// 이 생성자로 인해comment id가 제대로 전달됨

		try {
			commentSvc.deleteByCommentID(comment);
			status = HttpStatus.OK;
			resultMap.put("message", "정상삭제완료");
		} catch (SQLException e) {
			resultMap.put("message", "서버오류삭제실패");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

}
