package com.mindwiki.dao;


import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.mindwiki.model.ProfileDto;

@Repository
public interface LoginDao {

	ProfileDto login(ProfileDto member) throws SQLException;
	
}
