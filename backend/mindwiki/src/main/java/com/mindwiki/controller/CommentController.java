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

@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki")
public class CommentController {

	@Autowired
	private JwtService jwtSvc;
	
	@Autowired
	private CommentService commentSvc;
	
	
	
	@PostMapping("/mind/{no}/comment/make")
	public ResponseEntity<Map<String, Object>> make(HttpSession hs,
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="data", required=false) String data,
			@PathVariable int no
			) throws UnsupportedEncodingException{
		
		
		Map<String, Object> claims = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		int MindID=no;
		
		
		HttpStatus status=null;
		

		try {
			if(jwtSvc.verifyJWT(jwt)!=null) {
			
			claims=jwtSvc.verifyJWT(jwt);
			String email=(String) claims.get("email");
			System.out.println("이메일 jwt에서 검증후 잘가져왔는지" +email);
			CommentDto comment =new CommentDto(MindID,data,email);
			commentSvc.make(comment);//comment 생성
			resultMap.put("message", "comment가 등록되었습니다.");
			System.out.println("등록됨");
			status = HttpStatus.OK;
			}else {
				resultMap.put("message", "comment 등록실패(회원가입정보가 없습니다.)");
				status = HttpStatus.OK;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			//returnMessage="코멘트 등록 실패!";
		}
	
		
		System.out.println("일단 comment controller");

	
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//Comment read 해당되는 MindIDno에 있는 모든 것을 불러온다.
		@GetMapping("/mind/{no}/comment/read")public ResponseEntity<List<CommentDto>> read(@PathVariable int no) throws SQLException{
			
			int MindID=no;
			
			System.out.println(commentSvc.read(MindID));
			
			return new ResponseEntity<List<CommentDto>>(commentSvc.read(MindID), HttpStatus.OK);
			
		}
		
		
		//mind update
		@PutMapping("/mind/{no}/comment/update")public ResponseEntity<Map<String, Object>> update(
				@RequestParam(value="jwt", required=false) String jwt,
				@PathVariable int no,
				@RequestParam(value="commentID", required=false) int commentID,
				@RequestParam(value="data", required=false) String data) throws UnsupportedEncodingException{
			
			Map<String, Object> claims = new HashMap<>();
			Map<String, Object> resultMap = new HashMap<>();
			int MindID=no;
			
			claims=jwtSvc.verifyJWT(jwt);
			String email=(String) claims.get("email");
			
			
			System.out.println(MindID);
			CommentDto comment =new CommentDto(MindID,commentID,data,email);
			HttpStatus status=null;
			
			
			try {
				if(email!=null) {//여기 수정해야됨 세션이나 관리자로 권한확인
				commentSvc.updateByCommentID(comment);
				resultMap.put("message", "댓글이 수정되었습니다.");
				System.out.println("수정됨");
				status = HttpStatus.OK;
				}else {
					resultMap.put("message", "댓글 수정실패(로그인해주세요.)");
					status = HttpStatus.OK;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				status=HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
				//returnMessage="마인드 등록 실패!";
			}
		
		
			
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
			
			
		}
		
		
		//mind delete
		@DeleteMapping("/mind/{no}/comment/delete")public ResponseEntity<Map<String, Object>> delete(HttpSession hs,
				@PathVariable int no,
				@RequestParam(value="jwt", required=false) String jwt,
				@RequestParam(value="commentID", required=false) int commentID,
				@RequestParam(value="data", required=false) String data) throws UnsupportedEncodingException{
			
			Map<String, Object> claims = new HashMap<>();
			Map<String, Object> resultMap = new HashMap<>();
			int MindID=no;
			
			claims=jwtSvc.verifyJWT(jwt);
			String email=(String) claims.get("email");
			HttpStatus status=null;
			
			//1번 mymind 불러오기
			//2번 
			CommentDto comment =new CommentDto(MindID,commentID,data,email);
			//이 생성자로 인해comment id가 제대로 전달됨
			
			
			try {
				commentSvc.deleteByCommentID(comment);
				status = HttpStatus.OK;
				resultMap.put("message", "정상삭제완료");
			} catch (SQLException e) {
				resultMap.put("message", "서버오류삭제실패");
				status=HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
			
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
			
		}
	
}
