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
