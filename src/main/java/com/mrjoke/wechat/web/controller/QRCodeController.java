package com.mrjoke.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrjoke.wechat.domain.QRCode;
import com.mrjoke.wechat.service.IAccessTokenService;
import com.mrjoke.wechat.utils.Constants;
import com.mrjoke.wechat.utils.HttpServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/QRCode")
public class QRCodeController {
    @Autowired
    @Qualifier("accessTokenService")
    private IAccessTokenService accessTokenService;

    /**
     * 创建带参数的二维码
     *
     * @return 返回创建结果，json数据
     **/
    @GetMapping("/create")
    @ResponseBody
    public Object create() throws InterruptedException {
        //获取access_token
        String access_token = accessTokenService.token();
        Random random = new Random();
        //设置二维码的属性
        QRCode qrCode = new QRCode();
        qrCode.setExpire_seconds(3);
        qrCode.setAction_name("QR_SCENE");
        int scene_id = random.nextInt(10000);
        qrCode.setScene_id(scene_id);
        String content = JSONObject.toJSONString(qrCode);
        //向微信后台请求生成二维码url
        String result = HttpServiceUtil.post(Constants.QRCODE_CREATE, access_token, content);
        //返回结果
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }
}
