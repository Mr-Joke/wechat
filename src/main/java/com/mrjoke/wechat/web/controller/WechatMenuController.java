package com.mrjoke.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrjoke.wechat.service.IAccessTokenService;
import com.mrjoke.wechat.utils.Constants;
import com.mrjoke.wechat.utils.FileUtil;
import com.mrjoke.wechat.utils.HttpServiceUtil;
import org.apache.http.protocol.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sword.wechat4j.token.TokenProxy;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class WechatMenuController {
    @Autowired
    @Qualifier("accessTokenService")
    private IAccessTokenService accessTokenService;

    /**
     * 获取公众号菜单信息
     *
     * @return 返回菜单信息，json数据
     **/
    @GetMapping
    @ResponseBody
    public Object menus(){
        //获取access_token
        String access_token = accessTokenService.token();
        if (access_token == null || access_token.equals("")){
            Map<String,Object> map = new HashMap<>();
            map.put("result","token获取失败");
            return map;
        }
        //调用查询菜单接口
        String result = HttpServiceUtil.get(Constants.MENU_QUERY,access_token);
        System.out.println(result);
        //将返回的数据转换成JSONObject对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        //返回json数据
        return jsonObject;
    }

    /**
     * 删除公众号菜单信息
     *
     * @return 返回删除结果，json数据
     **/
    @GetMapping("/delete")
    @ResponseBody
    public Object delete(){
        //获取access_token
        String access_token = accessTokenService.token();
        //向微信后台请求删除菜单
        String result = HttpServiceUtil.get(Constants.MENU_DELETE,access_token);
        //返回状态码及结果
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    /**
     * 创建微信公众号菜单
     *
     * @return 返回创建结果，json数据
     **/
    @GetMapping("/create")
    @ResponseBody
    public Object create(){
        //获取access_token
        String access_token = accessTokenService.token();
        //获取菜单列表的数据
        String content = FileUtil.read("C:/Users/G110370/Desktop/menu.json");
        System.out.println(content);
        //向微信创建菜单
        String result = HttpServiceUtil.post(Constants.MENU_CREATE, access_token, content);
        //返回状态码及结果
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }
}
