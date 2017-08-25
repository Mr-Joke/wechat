package com.mrjoke.wechat.web.controller;

import com.mrjoke.wechat.service.impl.WechatServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.common.ValidateSignature;
import org.sword.wechat4j.param.SignatureParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WechatMessageController {
    Logger logger = Logger.getLogger(WechatMessageController.class);

    /**
     * 接收微信后台转发过来的信息
     *
     * @param request http请求
     * @param response http响应
     **/
    @RequestMapping(value = "/message",method = {RequestMethod.GET,RequestMethod.POST})
    public void reciever(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WechatServiceImpl wechatService = new WechatServiceImpl(request);
        String result = wechatService.execute();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(result);
    }
}
