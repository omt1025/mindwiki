/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : mind controll
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.mindwiki.model.ActiveDto;
import com.mindwiki.model.LikeDto;

import com.mindwiki.model.MindDto;
import com.mindwiki.model.NodeDto;
import com.mindwiki.model.NodeResultDto;
import com.mindwiki.service.JwtService;
import com.mindwiki.service.MindService;
import com.mindwiki.service.NodeService;

@RestController
@RequestMapping("/mindwiki")
public class MindController {

	@Autowired
	private MindService mindSvc;

	@Autowired
	private NodeService nodeService;

	@Autowired
	private JwtService jwtSvc;

	// 현재페이지를 스크랩함
	@PostMapping("/mind/scrap/{no}")
	public ResponseEntity<Map<String, Object>> scrap(@PathVariable(name = "no", required = false) int no,
			@RequestParam(value = "disScrap", required = false) int disScrap,
			@RequestParam(value = "jwt", required = false) String jwt) throws UnsupportedEncodingException {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email = (String) clamMap.get("email");

		if (disScrap == 1) {// 취소해야되는 값을 넘겨주면 여기서 로직을 끝내버리면됨
			try {
				mindSvc.deleteScrap(no, email);
				resultMap.put("message", "DISSCRAP");
				status = HttpStatus.OK;
			} catch (SQLException e) {

				resultMap.put("message", "ERROR");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}

		try {

			mindSvc.scrap(no, email);
			resultMap.put("message", "SCRAP");
			status = HttpStatus.OK;

		} catch (SQLException e) {
			resultMap.put("message", "duplicated");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 현재페이지를 좋아요함
	@PostMapping("/mind/like/{no}")
	public ResponseEntity<Map<String, Object>> like(@PathVariable(name = "no", required = false) int no,
			@RequestParam(value = "disLike", required = false) int disLike,
			@RequestParam(value = "jwt", required = false) String jwt) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email = (String) clamMap.get("email");

		if (disLike == 1) {// 취소해야되는 값을 넘겨주면 여기서 로직을 끝내버리면됨
			try {
				mindSvc.deleteLike(no, email);
				resultMap.put("message", "DISLIKE");
				status = HttpStatus.OK;
			} catch (SQLException e) {

				resultMap.put("message", "ERROR");
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				e.printStackTrace();
			}
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}

		try {

			mindSvc.like(no, email);
			resultMap.put("message", "LIKE");
			status = HttpStatus.OK;

		} catch (SQLException e) {
			resultMap.put("message", "duplicated");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 임시
	@GetMapping("/mind/scrap/read")
	public ResponseEntity<List<MindDto>> scrapRead(@RequestParam(value = "jwt", required = false) String jwt)
			throws UnsupportedEncodingException, SQLException {

		HttpStatus status = null;

		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email = (String) clamMap.get("email");

		try {

			mindSvc.scrapRead(email);

			status = HttpStatus.OK;

		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<List<MindDto>>(mindSvc.scrapRead(email), HttpStatus.OK);
	}

	// 임시
	@GetMapping("/mind/like/read")
	public ResponseEntity<List<MindDto>> likeRead(@RequestParam(value = "jwt", required = false) String jwt)
			throws UnsupportedEncodingException, SQLException {

		HttpStatus status = null;

		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email = (String) clamMap.get("email");

		status = HttpStatus.OK;

		return new ResponseEntity<List<MindDto>>(mindSvc.likeRead(email), HttpStatus.OK);
	}

	public String fileUpload(MultipartFile file) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		String plusTime = format1.format(time);

		String dir = "/src/main/resources/static/img";
		String path = System.getProperty("user.dir");
		// 리눅스는 / 윈도우는 \이다. 이거지금 리눅스처럼해도 잘돌아가게해줌

		String rootPath = path + dir;

		String filePath = rootPath + "/";

		Path directory = Paths.get(filePath).toAbsolutePath().normalize();

		plusTime.replaceAll(":", "");
		
		String fileOriginName=file.getOriginalFilename();
		
		if(fileOriginName.length()>5) {
			fileOriginName.substring(0,5);
		}
		String fileName = StringUtils.cleanPath(plusTime + fileOriginName);//시간을 추가한 네임

		String pathDB = "http://localhost:8000/mindwiki/image/" + fileName;

		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");

		Path targetPath = directory.resolve(fileName).normalize();

		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		try {
			file.transferTo(targetPath);
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return pathDB;// 어차피 업로드 로직은 실행되었기때문에 db에 넣을 thumbnail값을 출력한다.
	}

	// feed image 반환하기
	@GetMapping(value = "/image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {

		String dir = StringUtils.cleanPath("src/main/resources/static/img/");
		InputStream imageStream = new FileInputStream(dir + imagename);

		byte[] imageByteArray = IOUtils.toByteArray(imageStream);

		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);

	}

	@GetMapping("/mind/hashtag/read/{no}")
	public List<String> hashRead(@PathVariable(name = "no", required = false) int MindID,
			@RequestParam(value = "jwt", required = false) String jwt) {

		List<String> list = new ArrayList<>();
		try {
			list = mindSvc.hashtagByMindID(MindID);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	@DeleteMapping("/mind/hashtag/delete/{no}")
	public ResponseEntity<Map<String, Object>> hashRead(@PathVariable(name = "no", required = false) int MindID,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "hashtag", required = false) String hashtag) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			mindSvc.deleteHashtag(MindID, hashtag);
			resultMap.put("message", "DELETE");
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/mind")
	public ResponseEntity<Map<String, Object>> make(HttpSession hs,
			@RequestParam(value = "files", required = false) MultipartFile file,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "hashtag", required = false) String hashtag,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "explanation", required = false) String explanation)
			throws UnsupportedEncodingException {

		Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);

		String admin = (String) claimMap.get("email");

		MindDto mind = new MindDto(admin, title, hashtag, subject, explanation);
		int MindID = 0;

		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();
		String thumbnail = "";
		try {
			if (jwtSvc.verifyJWT(jwt) != null) {

				if (file == null) {

					mindSvc.make(mind);

					/* 노드 초기화 부분입니다. 초기화가 비정상이면 종료합니다 */
					MindID = mindSvc.getMindID(admin);
					NodeResultDto result = nodeService.initNode(MindID, subject, hashtag);
					if (result.getResult() != "SUCCESS") {
						resultMap.put("message", MindID);

						status = HttpStatus.OK;
						return new ResponseEntity<Map<String, Object>>(resultMap, status);
					}

					StringTokenizer st = new StringTokenizer(hashtag, ",");
					int count = 0;
					count = st.countTokens();
					for (int i = 0; i < count; i++) {
					
						mindSvc.makeHashtag(MindID, st.nextToken());
					}

					resultMap.put("message", MindID);

					status = HttpStatus.OK;
					return new ResponseEntity<Map<String, Object>>(resultMap, status);
				} else {

					thumbnail = fileUpload(file);
					mind.setThumbnail(thumbnail);
				}

				mindSvc.make(mind);

				/* 노드 초기화 부분입니다. 초기화가 비정상이면 종료합니다 */
				MindID = mindSvc.getMindID(admin);
				NodeResultDto result = nodeService.initNode(MindID, subject, hashtag);
				if (result.getResult() != "SUCCESS") {
					resultMap.put("message", "NODE_INIT_FAIL");
					status = HttpStatus.OK;
					return new ResponseEntity<Map<String, Object>>(resultMap, status);
				}

				resultMap.put("message", "SUCCESS");
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "FAIL");
				status = HttpStatus.OK;
			}
		} catch (SQLException e) {

			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();

		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PutMapping("/read/{no}/comment")
	public ResponseEntity<List<MindDto>> comment(HttpSession hs, @PathVariable int no) throws SQLException {


		return new ResponseEntity<List<MindDto>>(mindSvc.read(), HttpStatus.OK);

	}

	@GetMapping("/mind/read/{no}")
	public ResponseEntity<MindDto> detailMind(@PathVariable int no,
			@RequestParam(value = "flag", required = false) int flag) throws SQLException {

		System.out.println(no);

		if (flag == 1) {// 1이라면, 조회수 안올라가는거
			return new ResponseEntity<MindDto>(mindSvc.readByMindIDNoCount(no), HttpStatus.OK);
		} else if (flag == 0) {// 0이라면 조회수가 올라가는거
			return new ResponseEntity<MindDto>(mindSvc.readByMindID(no), HttpStatus.OK);
		} else {// 이건 없는경우일듯
			return new ResponseEntity<MindDto>(mindSvc.readByMindID(no), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/mind/read/profilepic/{no}")
	public ResponseEntity<Map<String, Object>> detailMindProfile(@PathVariable int no) throws SQLException {

		Map<String, Object> resultMap = new HashMap<>();

		resultMap.put("pic", mindSvc.getMindPorfilePic(no));

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}


	@GetMapping("/mind/read")
	public ResponseEntity<List<MindDto>> read(HttpSession hs) throws SQLException {


		return new ResponseEntity<List<MindDto>>(mindSvc.read(), HttpStatus.OK);

	}

	// mind read by email mymind 구현완료
	@PostMapping("/mind/list")
	public ResponseEntity<List<MindDto>> readByEmail(@RequestParam(value = "jwt", required = false) String jwt,
			HttpSession hs) throws SQLException {

		String email = null;
		try {
			Map<String, Object> claimMap = jwtSvc.verifyJWT(jwt);
			email = (String) claimMap.get("email");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 마인드 내부에 들어오면 이 페이지의 mindID가 무엇인지 알려줘야함
		System.out.println(mindSvc.readByEmail(email));

		return new ResponseEntity<List<MindDto>>(mindSvc.readByEmail(email), HttpStatus.OK);

	}

//mind update
	@PutMapping("/mind/update")
	public ResponseEntity<Map<String, Object>> update(HttpSession hs,
			@RequestParam(value = "MindID", required = false) int MindID,
			@RequestParam(value = "files", required = false) MultipartFile file,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "hashtag", required = false) String hashtag,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "explanation", required = false) String explanation) {

		System.out.println(MindID);

		MindDto mind = new MindDto(MindID, title, hashtag, subject, explanation);
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();

		try {
			if (title != null) {
				if (file != null) {// 파일이 비어있지 않으면 바로 파일을 업로드 해준다.
					String thumbnail = fileUpload(file);
					mind.setThumbnail(thumbnail);
				}
				mindSvc.update(mind);

				mindSvc.deleteHashtagList(MindID);

				StringTokenizer st = new StringTokenizer(hashtag, ",");
				int count = 0;
				count = st.countTokens();
				for (int i = 0; i < count; i++) {// 어차피 0일리는 없음

					mindSvc.makeHashtag(MindID, st.nextToken());// 해쉬태그들을 리스트로 넣음
				}

				resultMap.put("message", "마인드가 수정되었습니다.");
				System.out.println("수정됨");
				status = HttpStatus.OK;
			} else {
				resultMap.put("message", "마인드 수정실패(로그인해주세요.)");
				status = HttpStatus.OK;
			}
		} catch (SQLException e) {

			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();

		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// mind delete
	@DeleteMapping("/mind/delete/{no}")
	public ResponseEntity<Map<String, Object>> delete(HttpSession hs,
			@PathVariable(name = "no", required = false) int no,
			@RequestParam(value = "jwt", required = false) String jwt) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		MindDto mind = new MindDto();
		mind.setMindID(no);
		System.out.println("삭제번호 " + no);
		try {
			mindSvc.delete(mind);
			status = HttpStatus.OK;
			resultMap.put("message", "정상삭제완료");
		} catch (SQLException e) {
			resultMap.put("message", "서버오류삭제실패");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/capture/tuhmbnail")
	public ResponseEntity<Map<String, Object>> capture_upload(
			@RequestParam(value = "files", required = false) MultipartFile file,
			@RequestParam(value = "jwt", required = false) String jwt,
			@RequestParam(value = "MindID", required = false) int MindID) {

		Map<String, Object> resultMap = new HashMap<>();

		HttpStatus status = null;
		try {
			if (file != null) {
				String thumbnail = fileUpload(file);
				System.out.println(thumbnail);
				mindSvc.updateThumbNail(thumbnail, MindID);
				
				
				status = HttpStatus.OK;
				resultMap.put("message", "SUCCESS");
			} else {
				resultMap.put("message", "FAIL");
			}
		} catch (SQLException e) {
			resultMap.put("message", "FAIL");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	
	// 내 마인드 스크랩, 좋아요 한 목록 받아오기
	@PostMapping("/read/active")
	public ResponseEntity<List<ActiveDto>> readActiveList(HttpSession hs,
			@RequestParam(value = "jwt", required = false) String jwt)
			throws SQLException, UnsupportedEncodingException {

		Map<String, Object> clamMap = jwtSvc.verifyJWT(jwt);
		String email = (String) clamMap.get("email");

		return new ResponseEntity<List<ActiveDto>>(mindSvc.readActiveList(email), HttpStatus.OK);

	}
	
	

}
