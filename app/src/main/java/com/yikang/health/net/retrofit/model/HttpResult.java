package com.yikang.health.net.retrofit.model;

/**
 * Created by zwb on 2016/5/30.
 */
public class HttpResult<T> {
    private String error;
    private String message;
    private T data;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return "0".equals(error);
    }
}
