package com.mindwiki.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mindwiki.model.MindDto;


public interface LiketagService {
	public String list(String email) throws SQLException;
	public void update(String hashtag, String email) throws SQLException;
	public List<MindDto> likeTagAndMind(String hashtag) throws SQLException;
}
