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

    @RequestMapping(value = "/message",method = {RequestMethod.GET,RequestMethod.POST})
    public void reciever(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("request encoding : " + request.getCharacterEncoding());
        WechatServiceImpl wechatService = new WechatServiceImpl(request);
        String result = wechatService.execute();
        response.setCharacterEncoding("UTF-8");
        logger.info("response encoding : " + response.getCharacterEncoding());
        response.getWriter().println(result);
    }
}
