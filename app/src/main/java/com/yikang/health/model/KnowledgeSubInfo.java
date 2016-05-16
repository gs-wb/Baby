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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(this.name);
		dest.writeString(this.parentId);
	}

	protected KnowledgeSubInfo(Parcel in) {
		super(in);
		this.name = in.readString();
		this.parentId = in.readString();
	}

	public static final Creator<KnowledgeSubInfo> CREATOR = new Creator<KnowledgeSubInfo>() {
		@Override
		public KnowledgeSubInfo createFromParcel(Parcel source) {
			return new KnowledgeSubInfo(source);
		}

		@Override
		public KnowledgeSubInfo[] newArray(int size) {
			return new KnowledgeSubInfo[size];
		}
	};
}
