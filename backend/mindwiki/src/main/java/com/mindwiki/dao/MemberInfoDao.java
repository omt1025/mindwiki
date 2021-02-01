package com.mindwiki.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.ProfileDto;

@Repository
public interface MemberInfoDao {
	ProfileDto login(String email) throws SQLException;
}
