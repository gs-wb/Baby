package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwb on 2016/5/15.
 */
public class BabyLoreModel implements Parcelable {


    /**
     * count : 33
     * description : 涨知识 ： 春天精子最健康 怀孕好时机 冬季怀孕：中医不主张最开始提到的以色列的研究报告中，同时指出了正常人在冬天时每毫升精液中有7000万个精子，其中5%的精子游得很快;春天时每毫升精液中有6800万个精子，其中3%的精子游动速度很快
     * fcount : 0
     * id : 19569
     * img : /lore/160511/173f5ab9dba7ae9c541e5c13e509370c.jpg
     * keywords : 精子 怀孕 精液 季节 男性
     * loreclass : 6
     * rcount : 0
     * time : 1462968567000
     * title : 在春天时的精子质量最好
     */

    private int count;
    private String description;
    private int fcount;
    private int id;
    private String img;
    private String keywords;
    private int loreclass;
    private int rcount;
    private long time;
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLoreclass() {
        return loreclass;
    }

    public void setLoreclass(int loreclass) {
        this.loreclass = loreclass;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeString(this.description);
        dest.writeInt(this.fcount);
        dest.writeInt(this.id);
        dest.writeString(this.img);
        dest.writeString(this.keywords);
        dest.writeInt(this.loreclass);
        dest.writeInt(this.rcount);
        dest.writeLong(this.time);
        dest.writeString(this.title);
    }

    public BabyLoreModel() {
    }

    protected BabyLoreModel(Parcel in) {
        this.count = in.readInt();
        this.description = in.readString();
        this.fcount = in.readInt();
        this.id = in.readInt();
        this.img = in.readString();
        this.keywords = in.readString();
        this.loreclass = in.readInt();
        this.rcount = in.readInt();
        this.time = in.readLong();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<BabyLoreModel> CREATOR = new Parcelable.Creator<BabyLoreModel>() {
        @Override
        public BabyLoreModel createFromParcel(Parcel source) {
            return new BabyLoreModel(source);
        }

        @Override
        public BabyLoreModel[] newArray(int size) {
            return new BabyLoreModel[size];
        }
    };
}
