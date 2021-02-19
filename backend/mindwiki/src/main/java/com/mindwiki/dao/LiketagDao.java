/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : live tag
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindwiki.model.MindDto;



@Repository
public interface LiketagDao {

	
	public String list(String email) throws SQLException;
	public void update(String hashtag, String email) throws SQLException;
	public List<MindDto> likeTagAndMind(String hashtag) throws SQLException;
}
