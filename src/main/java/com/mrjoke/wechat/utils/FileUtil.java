package com.mrjoke.wechat.utils;

import java.io.*;

/**
 * 文件操作工具类
 **/
public class FileUtil {

    /**
     * 读取本地文本文件
     *
     * @param path 本地文件路径
     * @return 返回读取的文本信息
     **/
    public static String read(String path) {
        File file = new File(path);
        if (!file.exists()) throw new RuntimeException("文件不存在!");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
