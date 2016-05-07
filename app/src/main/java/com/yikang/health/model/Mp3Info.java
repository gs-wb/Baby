package com.yikang.health.model;

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
}
