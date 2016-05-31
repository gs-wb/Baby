package com.yikang.health.net.retrofit.model;

import java.util.List;

/**
 * Created by zwb on 2016/5/30.
 */
public class HttpLoreResult<T> {

    /**
     * status : true
     * total : 2053
     */

    private boolean status;
    private int total;
    private T tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getTngou() {
        return tngou;
    }

    public void setTngou(T tngou) {
        this.tngou = tngou;
    }
}
