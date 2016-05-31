package com.yikang.health.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zwb on 2016/5/15.
 */
public class BabyLoreModel implements Parcelable {


    /**
     * count : 11
     * description : 育儿小常识：宝宝妈妈必备的30个小常识！ 大家可以想别的方法：养鱼可以增加室内湿度；屋里放盆水；多擦几次灰；在暖气上放湿毛巾）24、1岁内不加味精25、一支炸鸡腿=60支香烟26、当室内温度》24度，铺在地上防凉的泡沫垫会释放出甲醛（我用同学家旧的）27、不要用浴霸，因为孩子仰脸，会伤孩子的眼
     * fcount : 0
     * id : 19609
     * img : /lore/160529/8bd6597a550ef69a9cb981c68c2fffb2.jpg
     * keywords : 孩子 婴儿 湿气 月内 推车
     * loreclass : 6
     * rcount : 0
     * time : 1464491878000
     * title : 太阳直晒脚心、妈妈的微笑会提高孩子的免疫力
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
