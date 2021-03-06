package com.yikang.health.net.retrofit.model;

/**
 * Created by zwb on 2016/5/30.
 */
public class HttpWeatherResult<T> {
    private String errNum;
    private String errMsg;
    private T retData;

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }

    public boolean isSuccess() {
        return "success".equals(errMsg);
    }
}
