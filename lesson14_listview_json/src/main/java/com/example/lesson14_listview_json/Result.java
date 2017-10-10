package com.example.lesson14_listview_json;

/**
 * Created by 怪蜀黍 on 2016/11/23.
 */


/**
 * public class Result {
 * private String state;
 * private String des;
 * private User data;
 * }
 */
//或者
public class Result<T> {
    private String state;
    private String des;
    private T data;

    public Result() {
    }

    public Result(String state, String des, T data) {
        this.state = state;
        this.des = des;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}