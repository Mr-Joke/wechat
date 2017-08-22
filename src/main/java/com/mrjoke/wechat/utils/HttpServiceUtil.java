package com.mrjoke.wechat.utils;

import org.apache.http.HttpConnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpServiceUtil {

    public static String get(String url,String accessToken){
        String newUrl = url.replace("ACCESS_TOKEN", accessToken);
        try {
            URL httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            if (conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                conn.disconnect();
                return sb.toString();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url,String accessToken,String content){
        String newUrl = url.replace("ACCESS_TOKEN", accessToken);
        try {
            URL httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset","UTF-8");
            conn.setUseCaches(false);
            if (content != null && !content.equals("")){
                OutputStream outputStream = conn.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                outputStreamWriter.write(content,0,content.length());
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            if (conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                conn.disconnect();
                return sb.toString();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
