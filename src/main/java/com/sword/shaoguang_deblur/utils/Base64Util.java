package com.sword.shaoguang_deblur.utils;
import java.util.Base64;

public class Base64Util {
    /**
     * 编码
     * @param item
     * @return
     */
    public static String base64_enc(String item) {
        return Base64.getEncoder().encodeToString(item.getBytes());
    }

    public static byte[] base64_dec(String item) {
//        return Base64.getDecoder().decode(item);
        return Base64.getMimeDecoder().decode(item);
    }
}