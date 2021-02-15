package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			System.out.println("WITHDRAWAL_FAIL_INCORRECT_PASSWORD");
			return result;
		}
		
		if(profileMapper.withdrawal(dto)!=SUCCESS) {
			System.out.println("WITHDRAWAL_FAIL_SERVER_ERROR");
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
			System.out.println("CHANGE_PASSWORD_FAIL_INCORRECT_PASSWORD");
			return result;
		}
		
		dto.setPassword(newPassword);
		if(profileMapper.updatePassword(dto)!=SUCCESS) {
			result.setResult("CHANGE_PASSWORD_FAIL_SERVER_ERROR");
			System.out.println("CHANGE_PASSWORD_FAIL_SERVER_ERROR");
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
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "kjw11036@naver.com";
		String hostSMTPpwd = "ssafy2021!@";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "kjw11036@naver.com";
		String fromName = "mindwiki_admin";
		String subject = "temp password";
		String msg = dto.getPassword();
		
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
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("send temp pw to email error : " + e);
			return FAIL;
		}
		return SUCCESS;
	}
	
	@Override
	public ProfileResultDto changeProfile(ProfileDto dto) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();

		if (session.getMapper(ProfileDao.class).updateProfile(dto) == 1) {
			resultDto.setResult("SUCCESS");
		} else {
			resultDto.setResult("FAIL");
		}
		return resultDto;
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

}