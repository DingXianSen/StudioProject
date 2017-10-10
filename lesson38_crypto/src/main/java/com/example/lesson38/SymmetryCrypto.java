package com.example.lesson38;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SymmetryCrypto {
    public static final int AES = 1;
    public static final int DES = 2;
    public static final int DES3 = 3;

    /**
     * 加密
     *
     * @param content 加密内容
     * @param key     密钥
     * @param type    加密类型
     */
    public static String encrypt(String content, String key, int type) {
        String typeStr = "AES";
        switch (type) {
            case DES:
                typeStr = "DES";
                break;
            case DES3:
                typeStr = "DESede";
                break;
            default:
                typeStr = "AES";
                break;
        }
        try {
//        将可以处理为二进制
            byte[] keyRaw = getKeyRaw(key, typeStr);

            SecretKeySpec ks = new SecretKeySpec(keyRaw, typeStr);
//        获取用来加密解密的对象
            Cipher cipher = Cipher.getInstance(typeStr);
//        参数1：操作类型，加密解密 2：密钥
            cipher.init(Cipher.ENCRYPT_MODE, ks, new IvParameterSpec(new byte[cipher.getBlockSize()]));
//        加密返回密文
            byte[] b = cipher.doFinal(content.getBytes("utf-8"));
            Log.e("aaaa", "=============加密返回密文b" + b.toString());
//        参数1：表示正负
            BigInteger bi = new BigInteger(1, b);
            return bi.toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
//        加密文件的时候
//        File file;
//        FileInputStream is = new FileInputStream(file);
//        MappedByteBuffer mbb =
//                is.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
//
//
//        File save;
//        is = new FileInputStream(save);
//        MappedByteBuffer out =
//                is.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, file.length());
//
//
//        cipher.doFinal(mbb);
    }


    public static String decrypt(String content, String key, int type) {
        String typeStr = "AES";
        switch (type) {
            case DES:
                typeStr = "DES";
                break;
            case DES3:
                typeStr = "DESede";
                break;
            default:
                typeStr = "AES";
                break;
        }
        try {
            byte[] keyRaw = getKeyRaw(key, typeStr);

            SecretKeySpec ks = new SecretKeySpec(keyRaw, typeStr);
            Cipher cipher = Cipher.getInstance(typeStr);
//        参数1：操作类型，加密解密 2：密钥
            cipher.init(Cipher.DECRYPT_MODE, ks, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] b = toByteArray(content);
            b = cipher.doFinal(b);
            return new String(b, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private static byte[] toByteArray(String content) {
//        两个字符变一个字节，长度减半
        byte[] b = new byte[content.length() / 2];
        for (int i = 0; i < b.length; i++) {
            String str = content.substring(i * 2, i * 2 + 2);
            b[i]=(byte) Integer.parseInt(str,16);

        }
        return b;
    }

    private static byte[] getKeyRaw(String key, String type) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException {
        KeyGenerator kg = KeyGenerator.getInstance(type);
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(key.getBytes("utf-8"));
        kg.init(sr);
//        生成key         //编码成字节
        return kg.generateKey().getEncoded();
    }
}
