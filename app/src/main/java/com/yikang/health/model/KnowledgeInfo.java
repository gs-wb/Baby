package com.yikang.health.model;

import android.os.Parcel;

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(this.name);
		dest.writeInt(this.icon);
		dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
	}

	protected KnowledgeInfo(Parcel in) {
		super(in);
		this.name = in.readString();
		this.icon = in.readInt();
		this.isSelected = in.readByte() != 0;
	}

	public static final Creator<KnowledgeInfo> CREATOR = new Creator<KnowledgeInfo>() {
		@Override
		public KnowledgeInfo createFromParcel(Parcel source) {
			return new KnowledgeInfo(source);
		}

		@Override
		public KnowledgeInfo[] newArray(int size) {
			return new KnowledgeInfo[size];
		}
	};
}
