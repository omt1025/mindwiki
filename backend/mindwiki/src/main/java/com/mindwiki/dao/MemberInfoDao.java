package com.mindwiki.dao;
/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 임시
* 최종 수정일: 2021.02.04.
*******************************************************************************/
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.ProfileDto;

@Repository
public interface MemberInfoDao {
	ProfileDto login(String email) throws SQLException;
}
