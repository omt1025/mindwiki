package com.mindwiki.service;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindwiki.dao.LoginDao;
import com.mindwiki.model.MemberDto;


@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SqlSession session;
	
	
	@Override
	public String login(MemberDto member) throws SQLException {
		String result=null;
		String pass=member.getPassword();
		String pw=session.getMapper(LoginDao.class).login(member);
		
		
		if(pass.equals(pw)) {
			result="OK";
			return result;
		}
		return null;
	}

}
