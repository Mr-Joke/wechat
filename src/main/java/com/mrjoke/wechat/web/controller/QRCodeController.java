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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/QRCode")
public class QRCodeController {
    @Autowired
    @Qualifier("accessTokenService")
    private IAccessTokenService accessTokenService;

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView){
        String access_token = accessTokenService.token();
        QRCode qrCode = new QRCode();
        qrCode.setExpire_seconds(604800);
        qrCode.setAction_name("QR_SCENE");
        qrCode.setScene_id(123L);
        String content = JSONObject.toJSONString(qrCode);
        System.out.println(content);
        String result = HttpServiceUtil.post(Constants.QRCODE_CREATE, access_token, content);
        JSONObject jsonObject = JSONObject.parseObject(result);
        modelAndView.addObject("json",jsonObject);
        modelAndView.setViewName("showQRCode");
        return modelAndView;
    }
}
