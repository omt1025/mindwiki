package com.mindwiki.model;

public class NodeDto {
	
	private int NodeID;
	private String title;
	private int depth;
	private String URL;
	private String memo;
	private int positiveCnt;
	private int negativeCnt;
	private float fx;
	private float fy;
	private int[] ConnectionList;
	private float cx;
	private float cy;
	
	public int getNodeID() {
		return NodeID;
	}
	public void setNodeID(int nodeID) {
		NodeID = nodeID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPositiveCnt() {
		return positiveCnt;
	}
	public void setPositiveCnt(int positiveCnt) {
		this.positiveCnt = positiveCnt;
	}
	public int getNegativeCnt() {
		return negativeCnt;
	}
	public void setNegativeCnt(int negativeCnt) {
		this.negativeCnt = negativeCnt;
	}
	public float getFx() {
		return fx;
	}
	public void setFx(float fx) {
		this.fx = fx;
	}
	public float getFy() {
		return fy;
	}
	public void setFy(float fy) {
		this.fy = fy;
	}
	public int[] getConnectionList() {
		return ConnectionList;
	}
	public void setConnectionList(int[] connectionList) {
		ConnectionList = connectionList;
	}

}
