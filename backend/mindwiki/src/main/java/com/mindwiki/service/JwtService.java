package com.mindwiki.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

import com.mindwiki.model.ProfileDto;
import com.mindwiki.model.OauthDto;

public interface JwtService {

	String createToken(String subject, String email, String nickName);
	
	Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException;
	
	ProfileDto takeMemberInfo(String email) throws SQLException;
//	String oauthCheck(OauthDto Oauth);
}
