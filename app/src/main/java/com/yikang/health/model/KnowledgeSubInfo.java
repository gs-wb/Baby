package com.yikang.health.model;


import android.os.Parcel;

public class KnowledgeSubInfo extends BaseModel{
	String name;
	String parentId;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public KnowledgeSubInfo(String id,String parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
}
