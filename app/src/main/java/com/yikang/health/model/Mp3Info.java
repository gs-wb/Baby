package com.yikang.health.model;

import android.os.Parcel;

/**
 * Created by admin on 2016/5/6.
 */
public class Mp3Info extends BaseModel {
//            "mp3_id": "1461569824637644126",
//            "user_id": "1406529820596295660",
//            "file_name": "宝宝睡前故事集",
//            "mp3_name": "宝宝睡前故事集---001-在养鸭场里.mp3",
//            "type": "mp3",
//            "size": "6",
//            "file_url": "http://7xsyyu.com2.z0.glb.clouddn.com/mp3/%E5%AE%9D%E5%AE%9D%E7%9D%A1%E5%89%8D%E6%95%85%E4%BA%8B%E9%",
//            "sumnum": "0",
//            "create_time": "2016-04-25 15:37:04"
    private String mp3_id;
    private String user_id;
    private String file_name;
    private String mp3_name;
    private String type;
    private int size;
    private String file_url;
    private int sumnum;
    private String create_time;
    private boolean isPlay;
    private boolean notPause;

    public void setMp3_id(String mp3_id) {
        this.mp3_id = mp3_id;
    }

    public String getMp3_id() {
        return mp3_id;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getSumnum() {
        return sumnum;
    }

    public void setSumnum(int sumnum) {
        this.sumnum = sumnum;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setMp3_name(String mp3_name) {
        this.mp3_name = mp3_name;
    }

    public String getMp3_name() {
        return mp3_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setIsPlay(boolean isPlay) {
        this.isPlay = isPlay;
    }

    public boolean isNotPause() {
        return notPause;
    }

    public void setNotPause(boolean notPause) {
        this.notPause = notPause;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mp3_id);
        dest.writeString(this.user_id);
        dest.writeString(this.file_name);
        dest.writeString(this.mp3_name);
        dest.writeString(this.type);
        dest.writeInt(this.size);
        dest.writeString(this.file_url);
        dest.writeInt(this.sumnum);
        dest.writeString(this.create_time);
        dest.writeByte(this.isPlay ? (byte) 1 : (byte) 0);
        dest.writeByte(this.notPause ? (byte) 1 : (byte) 0);
    }

    public Mp3Info() {
    }

    protected Mp3Info(Parcel in) {
        super(in);
        this.mp3_id = in.readString();
        this.user_id = in.readString();
        this.file_name = in.readString();
        this.mp3_name = in.readString();
        this.type = in.readString();
        this.size = in.readInt();
        this.file_url = in.readString();
        this.sumnum = in.readInt();
        this.create_time = in.readString();
        this.isPlay = in.readByte() != 0;
        this.notPause = in.readByte() != 0;
    }

    public static final Creator<Mp3Info> CREATOR = new Creator<Mp3Info>() {
        @Override
        public Mp3Info createFromParcel(Parcel source) {
            return new Mp3Info(source);
        }

        @Override
        public Mp3Info[] newArray(int size) {
            return new Mp3Info[size];
        }
    };
}
