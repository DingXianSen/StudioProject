package com.example.lesson39_other_jar.internet;

import java.util.List;

/**
 * Created by 怪蜀黍 on 2017/1/6.
 */

public class Result {
    public List<Programme> data;
    public String describe;
    public int state;

    public List<Programme> getData() {
        return data;
    }

    public void setData(List<Programme> data) {
        this.data = data;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
