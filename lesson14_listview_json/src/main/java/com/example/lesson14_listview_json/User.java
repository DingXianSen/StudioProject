package com.example.lesson14_listview_json;

/**
 * Created by 怪蜀黍 on 2016/11/23.
 */

public class User {
    private String photo;
    private String nmae;
    private String phone;

    public User() {
    }

    public User(String photo, String nmae, String phone) {
        this.photo = photo;
        this.nmae = nmae;
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNmae() {
        return nmae;
    }

    public void setNmae(String nmae) {
        this.nmae = nmae;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
