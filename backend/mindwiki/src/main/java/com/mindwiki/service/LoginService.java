package com.mindwiki.service;

import java.sql.SQLException;

import com.mindwiki.model.ProfileDto;

public interface LoginService {

	
	ProfileDto login(ProfileDto member) ;
	
	
}
