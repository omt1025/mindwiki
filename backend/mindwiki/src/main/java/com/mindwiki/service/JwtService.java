package com.mindwiki.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.mindwiki.model.MemberDto;
import com.mindwiki.model.OauthDto;

public interface JwtService {

	String createToken(String subject, String email, String nickName);
	
	Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException;
	
//	String oauthCheck(OauthDto Oauth);
}
