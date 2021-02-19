package com.mindwiki.model;

import org.json.simple.JSONArray;

public class NodeDto {
	
	private int NodeID;
	private int MindID;
	private String nodeString;
	private JSONArray nodeJson;
	private Object nodeObject;
	
	public Object getNodeObject() {
		return nodeObject;
	}
	public void setNodeObject(Object nodeObject) {
		this.nodeObject = nodeObject;
	}
	public String getNodeString() {
		return nodeString;
	}
	public void setNodeString(String nodeString) {
		this.nodeString = nodeString;
	}
	public int getNodeID() {
		return NodeID;
	}
	public void setNodeID(int nodeID) {
		NodeID = nodeID;
	}
	public JSONArray getNodeJson() {
		return nodeJson;
	}
	public void setNodeJson(JSONArray nodeJson) {
		this.nodeJson = nodeJson;
	}
	public int getMindID() {
		return MindID;
	}
	public void setMindID(int mindID) {
		MindID = mindID;
	}

}
