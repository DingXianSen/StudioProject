package com.example.work14;

/**
 * Created by 怪蜀黍 on 2016/11/24.
 */

public class User {
    private String name;
    private Integer age;
    private String message;
    private String photoUri;
    private String error;

    public User() {
    }

    public User(String name, Integer age, String message) {
        this.name = name;
        this.age = age;
        this.message = message;
    }
    public User(String name, Integer age, String message,String photoUri) {
        this.name = name;
        this.age = age;
        this.message = message;
        this.photoUri=photoUri;
    }
    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
