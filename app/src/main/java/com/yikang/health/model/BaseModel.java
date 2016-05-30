package com.yikang.health.model;

public class BaseModel {
	private String error;
	protected String id;
	private String message;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return "0".equals(error);
	}
}
