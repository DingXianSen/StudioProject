package com.example.lesson24_service;

/**
 * Created by 怪蜀黍 on 2016/12/14.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 普通的实体类
 */
public class User implements Parcelable {
    private int id;
    private String name;
    private int age;
    private String phone;

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
        phone = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(phone);
    }
}
