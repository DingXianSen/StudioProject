package com.example.lesson38;

/**
 * Created by 怪蜀黍 on 2017/1/5.
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要
 */
public class Digest {
    public static String md5(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes("utf-8"));
            byte[] b = md.digest();
            return new BigInteger(1, b).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String SHA1(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(content.getBytes("utf-8"));
            byte[] b = md.digest();
            return new BigInteger(1, b).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
