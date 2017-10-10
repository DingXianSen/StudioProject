package com.lhr.jiandou.model.bean;

/**
 * Created by ChinaLHR on 2016/12/15.
 * Email:13435500980@163.com
 */

public class MovieHttpResult<T> {

    private int count;
    private int start;
    private int total;

    //模仿Data
    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }
    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

}
