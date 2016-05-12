package com.yikang.health.map.domain;

public class OutMapPoint {

	public OutMapPoint() {
	}

	public OutMapPoint(double longitude, double latitude, float radius, String name) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
		this.name = name;
	}

	public void initAddrInfo(String addrStr,String city,String cityCode,String district,String province,String street,String streetNumber){
		this.addrStr = addrStr;
		this.city = city;
		this.cityCode = cityCode;
		this.district = district;
		this.province = province;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public double longitude;
	public double latitude;
	/*获取定位精度*/
	public float radius;
	public String name;

	/*获取详细地址信息*/
	public String addrStr;
	/*获取城市*/
	public String city;
	/*获取城市编码*/
	public String cityCode;
	/*获取区/县信息*/
	public String district;
	/*获取省份*/
	public String province;
	/*获取街道信息*/
	public String street;
	/*获取街道号码*/
	public String streetNumber;
}
