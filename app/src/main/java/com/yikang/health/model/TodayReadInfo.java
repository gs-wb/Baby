package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TodayReadInfo implements Parcelable {
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.title);
		dest.writeString(this.content);
		dest.writeInt(this.type);
		dest.writeString(this.imgUrl);
	}

	protected TodayReadInfo(Parcel in) {
		this.title = in.readString();
		this.content = in.readString();
		this.type = in.readInt();
		this.imgUrl = in.readString();
	}

	public static final Parcelable.Creator<TodayReadInfo> CREATOR = new Parcelable.Creator<TodayReadInfo>() {
		@Override
		public TodayReadInfo createFromParcel(Parcel source) {
			return new TodayReadInfo(source);
		}

		@Override
		public TodayReadInfo[] newArray(int size) {
			return new TodayReadInfo[size];
		}
	};
}
