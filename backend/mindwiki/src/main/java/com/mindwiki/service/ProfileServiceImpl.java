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
		ProfileResultDto resultDto = new ProfileResultDto();

		if (successRegister(dto)) {
			resultDto.setResult(SUCCESS);
		} else {
			resultDto.setResult(FAIL);
		}

		return resultDto;
	}

	@Override
	public ProfileResultDto withdrawal(ProfileDto dto) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();

		if (successWithdrawal(dto)) {
			resultDto.setResult(SUCCESS);
		} else {
			resultDto.setResult(FAIL);
		}

		return resultDto;
	}

	@Override
	public ProfileResultDto changePassword(ProfileDto dto, String newPassword) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();

		if (successChangePassword(dto, newPassword)) {
			resultDto.setResult(SUCCESS);
		} else {
			resultDto.setResult(FAIL);
		}

		return resultDto;
	}

	@Override
	public ProfileResultDto changeProfile(ProfileDto dto) throws SQLException {
		ProfileResultDto resultDto = new ProfileResultDto();

		if (successChangeProfile(dto)) {
			resultDto.setResult(SUCCESS);
		} else {
			resultDto.setResult(FAIL);
		}

		return resultDto;
	}
	
	@Override
	public ProfileResultDto getProfile(ProfileDto profileDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO
	// email 중복 검사
	// bookshelf 생성
	// mymindbook, scrapmindbook 생성
	// book 생성
	private boolean successRegister(ProfileDto dto) throws SQLException {
		if (session.getMapper(ProfileDao.class).register(dto) == 1)
			return true;
		return false;
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
