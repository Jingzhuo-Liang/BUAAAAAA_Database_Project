package com.evan.demo.utils;

import java.util.Random;

public class StringUtils {

    private static String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";

    //随机生成指定长度的字符串
    //未实现：生成的字符串可能相同，需要判定
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < length;i++) {
            sb.append(baseString.charAt(random.nextInt(baseString.length())));
        }
        return sb.toString();
    }
}
