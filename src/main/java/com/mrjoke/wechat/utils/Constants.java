package com.mrjoke.wechat.utils;

public class Constants {
    /******数据库表名******/
    public static final String TOKEN_TABLE = "tb_token";

    /******微信接口******/
    //菜单接口
    public static final String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    //生成带参数的二维码接口
    public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    public static final String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
}
