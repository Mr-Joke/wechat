package com.mrjoke.wechat.utils;

public class Constants {
    public static final String UPLOAD_ROOT_PATH = "/resources/materials/";
    /******数据库表名******/
    public static final String TOKEN_TABLE = "tb_token";
    public static final String MATERIAL_TABLE = "tb_material";

    /******微信接口******/
    //菜单接口
    public static final String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";//查询
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";//创建
    public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";//删除
    //生成带参数的二维码接口
    public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";//创建
    public static final String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";//展示
    //素材接口
    public static final String TEMP_MATERIAL_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";//上传临时素材
    public static final String TEMP_MATERIAL_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";//获取临时素材
    public static final String FOREVER_MATERIAL_UPLOAD = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";//上传永久素材
    public static final String FOREVER_MATERIAL_GET = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";//获取永久素材
    public static final String MATERIAL_DELETE = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";//删除素材

}
