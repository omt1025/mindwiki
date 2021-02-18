/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 서비스
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.model.ProfileDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SqlSession session;

	@Override
	public ProfileDto login(ProfileDto member) {
		String pass = member.getPassword();// 처음에 받아온 member
		ProfileDto temp_mem = new ProfileDto();
		// password dto로 넘겨줌
		try {
			temp_mem = session.getMapper(LoginDao.class).login(member);
			if (temp_mem != null) {
				member = temp_mem;
			}
		} catch (SQLException e) {

			member.setNickName(null);
			e.printStackTrace();
			return member;// 없으면 빈값 제출
		} // sql실행한 뒤에 member

		// 여기는 이메일이 존재하면 체크해줌 없으면 위에서 빈값으로 제출
		if (pass.equals(member.getPassword())) {

			return member;// 여기는 데이터를 불러온 member
		} else {

			member.setNickName(null);
			return member;// 여기는 데이터에 아무것도 없는 member
		}

	}

}
