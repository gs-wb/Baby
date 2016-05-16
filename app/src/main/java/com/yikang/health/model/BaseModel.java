package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseModel implements Parcelable {
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.error);
		dest.writeString(this.message);
		dest.writeString(this.id);
	}

	public BaseModel() {
	}

	protected BaseModel(Parcel in) {
		this.error = in.readInt();
		this.message = in.readString();
		this.id = in.readString();
	}

}
