package com.yikang.health.model;

import android.os.Parcel;

/**
 * Created by zwb on 2016/5/15.
 */
public class BabyLoreModel extends BaseModel{

    /*id	    long	ID编码
    keywords	string	关键词
    title	    string	标题
    description	string	简介
    img	        string	图片
    loreclass	int	    分类
    count	    int	    访问数
    rcount	    int	    评论数
    fcount	    int	    收藏数
    time	    long	发布时间*/

    private int count;
    private String description;
    private int fcount;
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
        super.writeToParcel(dest, flags);
        dest.writeInt(this.count);
        dest.writeString(this.description);
        dest.writeInt(this.fcount);
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
        super(in);
        this.count = in.readInt();
        this.description = in.readString();
        this.fcount = in.readInt();
        this.img = in.readString();
        this.keywords = in.readString();
        this.loreclass = in.readInt();
        this.rcount = in.readInt();
        this.time = in.readLong();
        this.title = in.readString();
    }

    public static final Creator<BabyLoreModel> CREATOR = new Creator<BabyLoreModel>() {
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
