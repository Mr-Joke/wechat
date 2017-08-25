package com.mrjoke.wechat.utils;

import java.util.Random;

public class RandomCodeUtil {
    public static final String BASE_1 = "0123456789abcdefghijklmnopqrstuvwsyzABCDEFGHIJKLMNOPQRSTUVWSYZ";
    public static final String BASE_2 = "0123456789abcdefghijklmnopqrstuvwsyz";
    public static final String BASE_3 = "0123456789";
    /**
     * 生成随机字符串
     * @param base 随机字符串的种子
     * @param length 长度
     * @return 返回特定长度的随机字符串
     **/
    public static String generate(String base,int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(base.length());
            char character = base.charAt(index);
            sb.append(character);
        }
        return sb.toString();
    }
}