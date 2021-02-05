package com.mindwiki.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.ProfileDao;
import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.ProfileResultDto;

@Service
public class ProfileServiceImpl implements ProfileService {

	// TODO
	// session -> DAO
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
		
		if(profileMapper.checkPassword(dto)!=SUCCESS) {
			result.setResult("WITHDRAWAL_FAIL_INCORRECT_PASSWORD");
			System.out.println("CHANGE_PASSWORD_FAIL_INCORRECT_PASSWORD");
			return result;
		}
		
		if(profileMapper.updatePassword(dto)!=1) {
			result.setResult("CHANGE_PASSWORD_FAIL_SERVER_ERROR");
			System.out.println("CHANGE_PASSWORD_FAIL_SERVER_ERROR");
			return result;
		}
		
		result.setResult("SUCCESS");
		return result;
	}

	@Override
	public ProfileResultDto changeProfile(ProfileDto dto) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();

		if (successChangeProfile(dto)) {
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

	// TODO
	// password 확인
	private boolean successWithdrawal(ProfileDto dto) throws SQLException {
		if (session.getMapper(ProfileDao.class).withdrawal(dto) == 1)
			return true;
		return false;
	}

	// TODO
	// old pw 확인 작업
	private boolean successChangePassword(ProfileDto dto, String newPassword) throws SQLException {
		dto.setPassword(newPassword);
		if (session.getMapper(ProfileDao.class).updatePassword(dto) == 1)
			return true;
		return false;
	}

	// TODO
	private boolean successChangeProfile(ProfileDto dto) throws SQLException {
		if (session.getMapper(ProfileDao.class).updateProfile(dto) == 1)
			return true;
		return false;
	}
}