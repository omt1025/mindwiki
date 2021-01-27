package com.mindwiki.service;

import java.sql.SQLException;

import com.mindwiki.model.MemberDto;

public interface LoginService {

	
	MemberDto login(MemberDto member) throws SQLException;
	
	
}
