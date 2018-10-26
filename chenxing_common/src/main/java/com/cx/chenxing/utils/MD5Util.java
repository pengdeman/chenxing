package com.cx.chenxing.utils;

import java.security.MessageDigest;

public class MD5Util {
    public static String MD5(String plaintext, String key) {
        MessageDigest messageDigest = null;
        try {
            plaintext += key;
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(plaintext.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }
}
