package com.mrjoke.wechat.utils;

import com.mrjoke.wechat.domain.Material;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpServiceUtil {
    //模拟浏览器上传文件时需要的分割界限
    private static final String BOUNDARY = "----WebKitFormBoundary" + RandomCodeUtil.generate(RandomCodeUtil.BASE_1,16);
    private static final String PREFFIX = "--";
    private static final String END = "\r\n";

    /**
     * 发送get请求
     *
     * @param url 微信服务器接口url
     * @param accessToken 微信accessToken
     * @return 返回响应结果
     **/
    public static String get(String url,String accessToken){
        return send(url,accessToken,"GET",null);
    }

    /**
     * 发送post请求
     *
     * @param url 微信服务器接口url
     * @param accessToken 微信accessToken
     * @param content 请求内容
     * @return 返回响应结果
     **/
    public static String post(String url,String accessToken,String content){
        return send(url,accessToken,"POST",content);
    }

    /**
     * 发送http请求
     *
     * @param url 微信服务器接口url
     * @param accessToken 微信accessToken
     * @param method 请求类型
     * @param content 请求内容
     * @return 返回响应结果
     **/
    private static String send(String url,String accessToken,String method,String content){
        String newUrl = url.replace("ACCESS_TOKEN", accessToken);
        try {
            //创建http连接
            URL httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            //设置连接属性
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            conn.setRequestProperty("Charset","UTF-8");
            conn.setUseCaches(false);
            //判断是否有内容，有则传输
            if (content != null && !content.equals("")){
                OutputStream outputStream = conn.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                outputStreamWriter.write(content,0,content.length());
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
            //接收请求返回的结果
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
                //返回结果
                return sb.toString();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传素材
     *
     * @param url 微信服务器接口url
     * @param accessToken 微信accessToken
     * @param filePath 文件所在路径
     * @param material 素材
     * @return 返回响应结果
     **/
    public static String upload(String url, String accessToken, String filePath, Material material) {
        String newUrl = url.replace("ACCESS_TOKEN", accessToken).replace("TYPE",material.getType());
        URL httpUrl = null;
        try {
            //创建http连接
            httpUrl = new URL(newUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            //设置连接属性
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset","UTF-8");
            conn.setRequestProperty("Connection","keep-alive");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
            //向服务器写文件上传开始分割界限
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
            //从本地读取文件，向服务器上传
            while ((len = fileInputStream.read(buf)) != -1){
                outputStream.write(buf,0,len);
            }
            fileInputStream.close();
            //写结束分割界限
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
