package com.yikang.health.model;

import java.io.Serializable;

public class KnowledgeInfo extends BaseModel{
	String name;
	int icon;
	boolean isSelected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public KnowledgeInfo(String id, String name, int icon,boolean isSelected) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.isSelected = isSelected;
	}
		
}
