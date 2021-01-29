package com.mindwiki.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.model.ProfileDto;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SqlSession session;
	
	
	@Override
	public ProfileDto login(ProfileDto member) throws SQLException {
		String result=null;
		String pass=member.getPassword();//처음에 받아온 member
		member=session.getMapper(LoginDao.class).login(member);//sql실행한 뒤에 member
		
		
		
		
		if(pass.equals(member.getPassword())) {
			
			return member;
		}else {
			
			return null;
			
		}
		
	}

}
