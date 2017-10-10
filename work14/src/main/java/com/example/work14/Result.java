package com.example.work14;

/**
 * Created by 怪蜀黍 on 2016/11/24.
 */

public class Result<T> {
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
