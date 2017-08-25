package com.mrjoke.wechat.utils;

import com.mrjoke.wechat.domain.Material;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServiceUtil {
    private static final String BOUNDARY = "----WebKitFormBoundary" + RandomCodeUtil.generate(RandomCodeUtil.BASE_1,16);
    private static final String PREFFIX = "--";
    private static final String END = "\r\n";

    public static String get(String url,String accessToken){
        return send(url,accessToken,"GET",null);
    }

    public static String post(String url,String accessToken,String content){
        return send(url,accessToken,"POST",content);
    }

    private static String send(String url,String accessToken,String method,String content){
        String newUrl = url.replace("ACCESS_TOKEN", accessToken);
        try {
            URL httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
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

    public static String upload(String url, String accessToken, String filePath, Material material) {
        String newUrl = url.replace("ACCESS_TOKEN", accessToken).replace("TYPE",material.getType());
        URL httpUrl = null;
        try {
            httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset","UTF-8");
            conn.setRequestProperty("Connection","keep-alive");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
            StringBuffer sb = new StringBuffer();
            sb.append(PREFFIX).append(BOUNDARY).append(END);
            sb.append("Content-Disposition: form-data; name=\"media\"; filename=\"" + material.getImage_name() +"\"").append(END);
            sb.append("Content-Type: image/jpeg").append(END);
            sb.append(END);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(sb.toString().getBytes());
            //读取图片,用于上传
            FileInputStream fileInputStream = new FileInputStream(filePath);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(buf)) != -1){
                outputStream.write(buf,0,len);
            }
            fileInputStream.close();
            outputStream.write(END.getBytes());
            outputStream.write((PREFFIX + BOUNDARY + PREFFIX + END).getBytes());
            outputStream.flush();
            outputStream.close();
            //获取微信后台返回的结果
            if (conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }
                bufferedReader.close();
                conn.disconnect();
                return stringBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
