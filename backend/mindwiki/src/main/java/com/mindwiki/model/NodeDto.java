package com.mindwiki.model;

public class NodeDto {
	
	private int NodeID;
	private String data;
	private int MindID;
	
	public int getNodeID() {
		return NodeID;
	}
	public void setNodeID(int nodeID) {
		NodeID = nodeID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getMindID() {
		return MindID;
	}
	public void setMindID(int mindID) {
		MindID = mindID;
	}

}
