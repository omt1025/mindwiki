/******************************************************************************
* 작성자 : 서울 2반 4팀 신충현
* 기능 : 스크랩
* 최종 수정일: 2021.02.04.
*******************************************************************************/
package com.mindwiki.model;

public class ScrapDto {

	private String email;
	private int scrapedMindID;
	
	
	public String getEamil() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getScrapedMindID() {
		return scrapedMindID;
	}
	public void setScrapedMindID(int scrapedMindID) {
		this.scrapedMindID = scrapedMindID;
	}
	@Override
	public String toString() {
		return "ScrapDto [email=" + email + ", scrapedMindID=" + scrapedMindID + "]";
	}
	
	
	
}
