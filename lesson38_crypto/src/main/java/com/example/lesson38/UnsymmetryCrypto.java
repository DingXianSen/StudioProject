package com.example.lesson38;

/**
 * Created by 怪蜀黍 on 2017/1/5.
 */

import android.os.Build;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * a=4 b=6
 * 非对称加密
 */
public class UnsymmetryCrypto {
    /**
     * 生成key
     *
     * @return 【publicKey,privateKey】公钥，私钥
     */
    public static String[] generatorKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//        生成密钥对
            KeyPair kp = kpg.genKeyPair();
            byte[] pub = kp.getPublic().getEncoded();//公钥
            byte[] pri = kp.getPrivate().getEncoded();//私钥
            String pubStr = Base64.encodeToString(pub, Base64.DEFAULT);
            String priStr = Base64.encodeToString(pri, Base64.DEFAULT);
            return new String[]{pubStr, priStr};
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密（私钥解密）
     *
     * @param privateKeyStr
     * @param content
     * @return
     */
    public static String decrypt(String privateKeyStr, String content) {
        try {
            byte[] keyRaw = Base64.decode(privateKeyStr, Base64.DEFAULT);
            PKCS8EncodedKeySpec key = new PKCS8EncodedKeySpec(keyRaw);
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(key);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.PRIVATE_KEY, privateKey);
            byte[] b = cipher.doFinal(toByteArray(content));
            return new String(b, "utf-8");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密（公钥）
     *
     * @param publicKeyStr
     * @param content
     * @return
     */
    public static String encrypt(String publicKeyStr, String content) {
        try {
            byte[] keyRaw = Base64.decode(publicKeyStr, Base64.DEFAULT);
            X509EncodedKeySpec key = new X509EncodedKeySpec(keyRaw);
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(key);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.PUBLIC_KEY, publicKey);
            byte[] b = cipher.doFinal(content.getBytes("utf-8"));
            return new BigInteger(1, b).toString(16);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] toByteArray(String content) {
//        两个字符变一个字节，长度减半
        byte[] b = new byte[content.length() / 2];
        for (int i = 0; i < b.length; i++) {
            String str = content.substring(i * 2, i * 2 + 2);
            b[i] = (byte) Integer.parseInt(str, 16);

        }
        return b;
    }
}
