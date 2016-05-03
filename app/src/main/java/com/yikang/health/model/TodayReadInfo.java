package com.yikang.health.model;

public class TodayReadInfo {
	String title;
	String content;
	int type;
	String imgUrl;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public TodayReadInfo(String title, String content, int type, String imgUrl) {
		super();
		this.title = title;
		this.content = content;
		this.type = type;
		this.imgUrl = imgUrl;
	}
	
}
