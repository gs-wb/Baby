package com.yikang.health.model;

import java.io.Serializable;

public class BaseModel implements Serializable{
	int error;
	String message;
	String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getError() {
		return error;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
