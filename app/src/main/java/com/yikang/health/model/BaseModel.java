package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseModel implements Parcelable {
	int error;
	String id;

	public BaseModel(){}
	public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
		@Override
		public BaseModel createFromParcel(Parcel in) {
			return new BaseModel(in);
		}

		@Override
		public BaseModel[] newArray(int size) {
			return new BaseModel[size];
		}
	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public BaseModel(String id, int error) {
		this.id = id;
		this.error = error;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.error);
		dest.writeString(this.id);
	}

	protected BaseModel(Parcel in) {
		this.error = in.readInt();
		this.id = in.readString();
	}

}
