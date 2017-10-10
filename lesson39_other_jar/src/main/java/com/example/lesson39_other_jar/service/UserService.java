package com.example.lesson39_other_jar.service;

import com.example.lesson39_other_jar.internet.Result;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 怪蜀黍 on 2017/1/6.
 */

public interface UserService {
    @GET("lessons")
//访问地址
    Call<String> login();//注解参数

    @GET("lessons")
//访问地址
    Call<Result> login(@Query("pageIndex") String pageIndex,
                       @Query("pageSize") String PageSize);//注解参数


    @FormUrlEncoded
    @POST("/json.json")
//访问地址
    Call<String> login2(@FieldMap Map<String, String> map);//注解参数


    @FormUrlEncoded
    @POST("/json.json")
//访问地址
    Call<String> login2(@Field(value = "account") String account,
                        @Field(value = "account") String pwd);//注解参数

    @Multipart
    @POST
    Call<String> upload(
            @Part(value = "photo") MultipartBody.Part file,
            @Part(value = "photo") MultipartBody.Part file2);


    @Multipart
    @POST
    Call<String> upload(@PartMap Map<String, Object> fileMap);

    @Multipart
    @POST
    Call<String> upload(@Part(value = "photo,\"filename\"=\"aaaa.jpg\"") RequestBody file);

}
