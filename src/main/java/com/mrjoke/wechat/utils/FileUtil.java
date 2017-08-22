package com.mrjoke.wechat.utils;

import java.io.*;

public class FileUtil {

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
