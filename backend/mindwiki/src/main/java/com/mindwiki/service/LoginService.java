package com.mindwiki.service;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : login interface
* 최종 수정일: 2021.02.04.
*******************************************************************************/
import java.sql.SQLException;

import com.mindwiki.model.ProfileDto;

public interface LoginService {

	
	ProfileDto login(ProfileDto member) throws SQLException;
	
	
}
