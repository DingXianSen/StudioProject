package com.toolsmi.lesson40_pay;

import android.os.Message;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zz on 2017/1/9.
 */

public class MD5 {
    public static String md5(String content) {
        try {
            if (content == null) return null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes("utf-8"));
            byte[] b = md.digest();
            return new BigInteger(1, b).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
