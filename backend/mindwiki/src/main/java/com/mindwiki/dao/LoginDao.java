package com.mindwiki.dao;


import java.sql.SQLException;
import org.springframework.stereotype.Repository;

import com.mindwiki.model.MemberDto;

@Repository
public interface LoginDao {

	MemberDto login(MemberDto member) throws SQLException;
	
}
