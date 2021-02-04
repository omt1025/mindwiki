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
import com.mindwiki.model.LikeDto;
import com.mindwiki.model.MindDto;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.MindService;

//@CrossOrigin("*")
@RestController
@RequestMapping("/mindwiki")
public class MindController {

	@Autowired
	private MindService mindSvc;
	
	@Autowired
	private JwtService jwtSvc;
	
	//현재페이지를 스크랩함
	@PostMapping("/mind/scrap/{no}")
	public ResponseEntity<Map<String, Object>> scrap(@PathVariable(name = "no",required=false) int no,
			@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status=null;
		
		
		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email=(String)clamMap.get("email");
		
		try {
			
			mindSvc.scrap(no, email);
			resultMap.put("message", "SCRAP");
			status = HttpStatus.OK;
			
			
		} catch (SQLException e) {
			resultMap.put("message", "데이터중복");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
	
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	
	
	
	//현재페이지를 좋아요함
		@PostMapping("/mind/like/{no}")
		public ResponseEntity<Map<String, Object>> like(@PathVariable(name = "no",required=false) int no,
				@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException{
			Map<String, Object> resultMap = new HashMap<>();
			HttpStatus status=null;
			
			
			Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
			String email=(String)clamMap.get("email");
			
			try {
				
				mindSvc.like(no, email);
				resultMap.put("message", "LIKE");
				status = HttpStatus.OK;
				
				
			} catch (SQLException e) {
				resultMap.put("message", "데이터중복");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
		
			
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
	
		
		//스크랩 읽기/by email
		@GetMapping("/mind/scrap/read")
		public ResponseEntity<List<ScrapDto>> scrapRead(@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException, SQLException{
			
			HttpStatus status=null;
			
			
			Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
			String email=(String)clamMap.get("email");
			
			
			try {
				
				mindSvc.scrapRead(email);
				
				status = HttpStatus.OK;
				
				
			} catch (SQLException e) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
		
			
			return new ResponseEntity<List<ScrapDto>>(mindSvc.scrapRead(email), HttpStatus.OK);
		}
		
		//좋아요 읽기/by email
		@GetMapping("/mind/like/read")
		public ResponseEntity<List<LikeDto>> likeRead(@RequestParam(value="jwt", required=false) String jwt) throws UnsupportedEncodingException, SQLException{
			
			HttpStatus status=null;
			
			
			Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
			String email=(String)clamMap.get("email");
			
		
				status = HttpStatus.OK;
		
		
			
				return new ResponseEntity<List<LikeDto>>(mindSvc.likeRead(email), HttpStatus.OK);
		}
		
		
	
	public String fileUpload(MultipartFile file) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
		Date time = new Date();
		String plusTime = format1.format(time);
		
		String dir = "/src/main/resources/static/img";
		String path = System.getProperty("user.dir");
	
		
		String rootPath = path+dir;
	
	    String filePath = rootPath + "/" ;
	    
	   
	 
	    
	    Path directory = Paths.get(filePath).toAbsolutePath().normalize();
	    
	    
	 // 파일명을 바르게 수정한다.
	    plusTime.replaceAll(":","");
		String fileName = StringUtils.cleanPath(plusTime+file.getOriginalFilename());

		String pathDB = "http://localhost:8000/mindwiki/image/"+fileName;
		
		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
	
		Path targetPath = directory.resolve(fileName).normalize();

		// 파일이 이미 존재하는지 확인하여 존재한다면 오류를 발생하고 없다면 저장한다.
		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		try {
			file.transferTo(targetPath);
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		return pathDB;
	}
	


	@GetMapping(value = "/image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
		
	
		String dir=StringUtils.cleanPath("src/main/resources/static/img/");
	InputStream imageStream = new FileInputStream( dir+ imagename); 
	
	byte[] imageByteArray = IOUtils.toByteArray(imageStream);
	
	imageStream.close(); 
	return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/mind")
	public ResponseEntity<Map<String, Object>> make(HttpSession hs,
			@RequestParam(value= "files", required = false) MultipartFile file,
			@RequestParam(value="jwt", required=false) String jwt,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="hashtag", required=false) String hashtag,
			@RequestParam(value="subject", required=false) String subject,
			@RequestParam(value="explanation", required=false) String explanation) throws UnsupportedEncodingException{
		
		
		Map<String, Object> claimMap=jwtSvc.verifyJWT(jwt);
		  
		String admin=(String) claimMap.get("email");
		
		MindDto mind = new MindDto(admin, title, hashtag, subject, explanation);
		HttpStatus status=null;
		Map<String, Object> resultMap = new HashMap<>();
		String thumbnail="";
		try {
			if(jwtSvc.verifyJWT(jwt)!=null) {
			
			
			if(file==null) {
				mindSvc.make(mind);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.OK;
				return new ResponseEntity<Map<String, Object>>(resultMap, status);
			}else {
				
				thumbnail=fileUpload(file);//파일업로드 	
				mind.setThumbnail(thumbnail);
			}
			
			mindSvc.make(mind);
			
			resultMap.put("message", "SUCCESS");
			System.out.println("등록됨");
			status = HttpStatus.OK;
			}else {
				resultMap.put("message", "FAIL");
				status = HttpStatus.OK;
			}
		} catch (SQLException e) {

			status=HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();

		}
		
	
		
		
		
		
		System.out.println("일단 mind controller");

		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	//comment
	@PutMapping("/mind/read/{no}/comment")public ResponseEntity<List<MindDto>> comment(HttpSession hs, @PathVariable int no) throws SQLException{
		
		
		
		System.out.println(mindSvc.read());
		
		return new ResponseEntity<List<MindDto>>(mindSvc.read(), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/mind/read/{no}")
	public ResponseEntity<MindDto> detailMind(@PathVariable int no) throws SQLException {
		System.out.println(no);
	
		
		return new ResponseEntity<MindDto>(mindSvc.readByMindID(no),HttpStatus.OK);
	}
	
	//mind read 임시조회 조회가 되어야 수정이되니까
	@GetMapping("/mind/read")public ResponseEntity<List<MindDto>> read(HttpSession hs) throws SQLException{
		
		System.out.println(mindSvc.read());
		
		return new ResponseEntity<List<MindDto>>(mindSvc.read(), HttpStatus.OK);
		
	}
	
	//mind read by email mymind 구현완료
	@PostMapping("/mind/list")public ResponseEntity<List<MindDto>> readByEmail(
			@RequestParam(value="jwt", required=false) String jwt,
			HttpSession hs) throws SQLException{
	
		String email=null;
		try {
			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);
			email=(String)claimMap.get("email");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//마인드 내부에 들어오면 이 페이지의 mindID가 무엇인지 알려줘야함 
		System.out.println(mindSvc.readByEmail(email));
		
		return new ResponseEntity<List<MindDto>>(mindSvc.readByEmail(email), HttpStatus.OK);
		
	}
		
		
	
	
	//mind update
	@PutMapping("/mind/update")public ResponseEntity<Map<String, Object>> update(HttpSession hs,
			@RequestParam(value="MindID", required=false) int MindID,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="hashtag", required=false) String hashtag,
			@RequestParam(value="subject", required=false) String subject,
			@RequestParam(value="explanation", required=false) String explanation,
			@RequestParam(value="jwt", required=false) String jwt){
		
		
		System.out.println(MindID);
		MindDto mind = new MindDto(MindID, title, hashtag, subject, explanation);
		HttpStatus status=null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			if(title!=null) {//여기 수정해야됨 세션이나 관리자로 권한확인
			mindSvc.update(mind);
			resultMap.put("message", "마인드가 수정되었습니다.");
			System.out.println("수정됨");
			status = HttpStatus.OK;
			}else {
				resultMap.put("message", "마인드 수정실패(로그인해주세요.)");
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
	@DeleteMapping("/mind/delete/{no}")public ResponseEntity<Map<String, Object>> delete(HttpSession hs,
			@PathVariable(name = "no",required=false) int no,
			@RequestParam(value="jwt", required=false) String jwt){
		
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus status=null;
		
		MindDto mind=new MindDto();
		mind.setMindID(no);
		System.out.println("삭제번호 "+no);
		try {
			mindSvc.delete(mind);
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
