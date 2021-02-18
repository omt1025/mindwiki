package com.mindwiki.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mindwiki.dao.ProfileDao;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private SqlSession session;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public ProfileResultDto register(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		
		if(profileMapper.sameEmailCnt(dto)!=0) {
			result.setResult("REGISTER_FAIL_ALREADY_EXISTS_EMAIL");
			return result;
		}
		
		if(profileMapper.register(dto)!=SUCCESS) {
			result.setResult("REGISTER_FAIL_SERVER_ERROR");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto withdrawal(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		
		if(profileMapper.checkPassword(dto)!=SUCCESS) {
			result.setResult("WITHDRAWAL_FAIL_INCORRECT_PASSWORD");
			return result;
		}
		
		if(profileMapper.withdrawal(dto)!=SUCCESS) {
			result.setResult("WITHDRAWAL_FAIL_SERVER_ERROR");
			return result;
		}
		
		System.out.println("SUCCESS");
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto changePassword(ProfileDto dto, String newPassword) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		System.out.println(dto);
		
		if(profileMapper.checkPassword(dto)!=SUCCESS) {
			result.setResult("WITHDRAWAL_FAIL_INCORRECT_PASSWORD");
			return result;
		}
		
		dto.setPassword(newPassword);
		if(profileMapper.updatePassword(dto)!=SUCCESS) {
			result.setResult("CHANGE_PASSWORD_FAIL_SERVER_ERROR");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto sendTempPassword(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		
		if(profileMapper.sameEmailCnt(dto)!=1) {
			result.setResult("EMAIL_ERROR");
			return result;
		}
		
		String tmpPW = createTmpPW();
		dto.setPassword(tmpPW);
		if(profileMapper.updatePassword(dto)!=SUCCESS) {
			result.setResult("UPDATE_PW_ERROR");
			return result;
		}
		
		if(sendTempPWToEmail(dto)==FAIL) {
			result.setResult("MAIL_SEND_FAIL");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}
	
	private String createTmpPW() {
		String pw = "";
		for(int i=0; i<12; i++) {
			pw += (char) ((Math.random() * 26) + 97);
		}
		return pw;
	}
	
	private int sendTempPWToEmail(ProfileDto dto){
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "mindwiki.manager@gmail.com";
		String hostSMTPpwd = "ssafy2021!@";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "mindwiki.manager@gmail.com";
		String fromName = "mindwiki_admin";
		String subject = "MindWiki temporary password";
		String mainText = writeMainText(dto);
		
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(false);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(dto.getEmail(), charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(mainText);
			email.send();
		} catch (Exception e) {
			System.out.println("send temp pw to email error : " + e);
			return FAIL;
		}
		return SUCCESS;
	}
	
	private String writeMainText(ProfileDto dto) {
		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h2 style='color: black;'>";
		msg += "<p>Your temporary password </p>";
		msg += "<h3 style='color: blue;'>";
		msg += "<p>" + dto.getPassword() + "</p>";
		msg += "<h2 style='color: black;'>";
		msg += "<p>Please change your password.</p></div>";
		
		return msg;
	}
	
	@Override
	public ProfileResultDto changeProfile(ProfileDto dto, MultipartFile file) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		// phonenumber
		// nickname
		// 사진
		if(hasFile(file)) {
			String filePath = getFilePath(file);
			dto.setProfileDefaultPic(filePath);
			
			if(profileMapper.updateProfileDefaultPic(dto)!=SUCCESS){
				resultDto.setResult("PIC_CHANGE_FAIL");
				return resultDto;
			}
				
		}

		if(dto.getPhoneNumber()!=null) {
			profileMapper.updatePhonenumber(dto);
		}
		
		if(dto.getNickName()!=null) {
			profileMapper.updateNickname(dto);
		}
		
		resultDto.setResult("SUCCESS");
		return resultDto;
		
	}
	
	private boolean hasFile(MultipartFile file) {
		return (file!=null);
	}
	
	public String getFilePath(MultipartFile file) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		String fileUploadTime = dateFormat.format(time);
		fileUploadTime.replaceAll(":", "");
		
		String fileOriginName=file.getOriginalFilename();
		
		if(fileOriginName.length()>5) {
			fileOriginName.substring(0,5);
		}
		String fileName = StringUtils.cleanPath(fileUploadTime + fileOriginName);//시간을 추가한 네임
		String filePath = "http://localhost:8000/mindwiki/image/" + fileName;

		String userDir = System.getProperty("user.dir") + "/src/main/resources/static/img/";
		Path directory = Paths.get(userDir).toAbsolutePath().normalize();
		
		Path targetPath = directory.resolve(fileName).normalize();
		
		try {
			file.transferTo(targetPath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	@Override
	public ProfileDto getProfile(ProfileDto profileDto) throws SQLException {
		
		return null;
	}


	@Override
	public ProfileResultDto isExist(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		
		// 해당 이메일로 가입된 계정이 존재하면,
		if(profileMapper.sameEmailCnt(dto)!=0) {
			result.setResult("EXIST");
			return result;
		}
		
		// 존재하지 않는경우
		result.setResult("NOT EXIST");
		return result;
	}
	
	@Override
	public List<ProfileDto> getAllProfile() throws SQLException {
		return session.getMapper(ProfileDao.class).memberList();
	}

	@Override
	public String getProfilePic(String email) throws SQLException {
	
		return session.getMapper(ProfileDao.class).getProfilePic(email);
	}
	
	@Override
	public ProfileResultDto getProfile(String email) throws Exception {
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		ProfileDto profile = profileMapper.getMyProfile(email);
		
		ProfileResultDto result = new ProfileResultDto();
		result.setProfileDto(profile);
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto getMyProfile(String email) throws Exception {
		ProfileDao profileMapper = session.getMapper(ProfileDao.class);
		ProfileDto profile = profileMapper.getMyProfile(email);
		
		ProfileResultDto result = new ProfileResultDto();
		result.setProfileDto(profile);
		result.setResult("SUCCESS");
		return result;
//		return session.getMapper(ProfileDao.class).getMyProfile(email);
	}

}