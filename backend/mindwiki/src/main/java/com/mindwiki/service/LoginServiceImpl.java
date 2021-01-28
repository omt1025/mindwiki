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
	public MemberDto login(MemberDto member) throws SQLException {
		String result=null;
		String pass=member.getPassword();//처음에 받아온 member
		member=session.getMapper(LoginDao.class).login(member);//sql실행한 뒤에 member
		
		//pass와 member.getPasswrod()는 시간상의 이유로 달라짐 변수두개안하고 한개로함
		
		
		if(pass.equals(member.getPassword())) {
			
			return member;
		}else {
			
			return null;
			
		}
		
	}

}
