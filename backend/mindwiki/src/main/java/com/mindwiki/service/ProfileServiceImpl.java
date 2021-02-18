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
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(dao.sameEmailCnt(dto)!=0) {
			result.setResult("FAIL");
			return result;
		}
		
		if(dao.register(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto withdrawal(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(dao.checkPassword(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		if(dao.withdrawal(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto changePassword(ProfileDto dto, String newPassword) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(dao.checkPassword(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		dto.setPassword(newPassword);
		if(dao.updatePassword(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto sendTempPassword(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(dao.sameEmailCnt(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		dto.setPassword(tempPassword());
		if(dao.updatePassword(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
		}
		
		if(sendTempPasswordViaEmail(dto)==FAIL) {
			result.setResult("FAIL");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}
	
	private String tempPassword() {
		String pw = "";
		for(int i=0; i<12; i++) {
			pw += (char) ((Math.random() * 26) + 97);
		}
		return pw;
	}
	
	private int sendTempPasswordViaEmail(ProfileDto dto){
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "mindwiki.manager@gmail.com";
		String hostSMTPpwd = "ssafy2021!@";

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
			e.printStackTrace();
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
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(hasFile(file)) {
			String filePath = getFilePath(file);
			dto.setProfileDefaultPic(filePath);
			
			if(dao.updateProfileDefaultPic(dto)!=SUCCESS){
				result.setResult("FAIL");
				return result;
			}
		}

		if(dto.getPhoneNumber()!=null && dao.updatePhonenumber(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
//			dao.updatePhonenumber(dto);
		}
		
		if(dto.getNickName()!=null && dao.updateNickname(dto)!=SUCCESS) {
			result.setResult("FAIL");
			return result;
//			dao.updateNickname(dto);
		}
		
		result.setResult("SUCCESS");
		return result;
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
	public ProfileResultDto getMyProfile(String email) throws Exception {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		ProfileDto dto = new ProfileDto();
		dto.setEmail(email);
		
		if(dao.sameEmailCnt(dto)!=0) {
			result.setResult("FAIL");
			return result;
		}
		
		result.setProfileDto(dao.getMyProfile(email));
		result.setResult("SUCCESS");
		return result;
//		return session.getMapper(ProfileDao.class).getMyProfile(email);
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
	public ProfileResultDto isExist(ProfileDto dto) throws SQLException {
		ProfileResultDto result = new ProfileResultDto();
		ProfileDao dao = session.getMapper(ProfileDao.class);
		
		if(dao.sameEmailCnt(dto)!=0) {
			result.setResult("EXIST");
			return result;
		}
		
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
	


}