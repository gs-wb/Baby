package com.yikang.health.model;

import android.os.Parcel;

/**
 * Created by admin on 2016/5/13.
 */
public class WeatherModel extends BaseModel {


    /**
     * city : 北京
     * pinyin : beijing
     * citycode : 101010100  //城市编码
     * date : 15-02-11  //日期
     * time : 11:00  //发布时间
     * postCode : 100000  //邮编
     * longitude : 116.391  //经度
     * latitude : 39.904  //维度
     * altitude : 33  //海拔
     * weather : 晴  //天气情况
     * temp : 10  //气温
     * l_tmp : -4
     * h_tmp : 10
     * WD : 无持续风向  //风向
     * WS : 微风(<10m/h)  //风力
     * sunrise : 07:12  //日出时间
     * sunset : 17:44
     */

    private String city;
    private String pinyin;
    private String citycode;
    private String date;
    private String time;
    private String postCode;
    private double longitude;
    private double latitude;
    private String altitude;
    private String weather;
    private String temp;
    private String l_tmp;
    private String h_tmp;
    private String WD;
    private String WS;
    private String sunrise;
    private String sunset;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getL_tmp() {
        return l_tmp;
    }

    public void setL_tmp(String l_tmp) {
        this.l_tmp = l_tmp;
    }

    public String getH_tmp() {
        return h_tmp;
    }

    public void setH_tmp(String h_tmp) {
        this.h_tmp = h_tmp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.city);
        dest.writeString(this.pinyin);
        dest.writeString(this.citycode);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.postCode);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.altitude);
        dest.writeString(this.weather);
        dest.writeString(this.temp);
        dest.writeString(this.l_tmp);
        dest.writeString(this.h_tmp);
        dest.writeString(this.WD);
        dest.writeString(this.WS);
        dest.writeString(this.sunrise);
        dest.writeString(this.sunset);
    }

    public WeatherModel() {
    }

    protected WeatherModel(Parcel in) {
        super(in);
        this.city = in.readString();
        this.pinyin = in.readString();
        this.citycode = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.postCode = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.altitude = in.readString();
        this.weather = in.readString();
        this.temp = in.readString();
        this.l_tmp = in.readString();
        this.h_tmp = in.readString();
        this.WD = in.readString();
        this.WS = in.readString();
        this.sunrise = in.readString();
        this.sunset = in.readString();
    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel source) {
            return new WeatherModel(source);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };
}
