package com.example.lesson39_other_jar.internet;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by 怪蜀黍 on 2017/1/6.
 */

public class MyFactory extends Converter.Factory {
    @Override//返回值从ResponseBody怎么转化为想要返回的数据
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == RequestBody.class) {
            return NOTHINGCONVERTER;
        } else if (type == String.class) {
            return STRING_CONVERTER;
        }else{
            return new JSONConverter(type);
        }
//        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override//请求的RequestBody怎么么转化为要上传的数据类型
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    /**
     * 不转化
     */
    private static Converter<ResponseBody, ResponseBody> NOTHINGCONVERTER = new Converter<ResponseBody, ResponseBody>() {
        @Override
        public ResponseBody convert(ResponseBody value) throws IOException {
            return value;
        }
    };

    /**
     * 转化为字符串返回
     */
    private static Converter<ResponseBody, String> STRING_CONVERTER = new Converter<ResponseBody, String>() {
        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    };

    class JSONConverter implements Converter<ResponseBody, Object> {
        private Type type;
        public JSONConverter(Type type) {
            this.type=type;
        }

        @Override
        public Object convert(ResponseBody value) throws IOException {
            return JSON.parseObject(value.string(),type);
        }
    }
}
