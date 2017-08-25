package com.mrjoke.wechat.service.impl;

import org.apache.log4j.Logger;
import org.sword.wechat4j.WechatSupport;

import javax.servlet.http.HttpServletRequest;

public class WechatServiceImpl extends WechatSupport {
    Logger logger = Logger.getLogger(WechatServiceImpl.class);
    public WechatServiceImpl(HttpServletRequest request) {
        super(request);
    }

    @Override
    protected void onText() {
        String content = wechatRequest.getContent().trim();
        this.logger.info("request content : " + content);
        if (content.equals("官网")){
            responseNew("酷漫居","孩子分房，就找酷漫居","http://www.kumanju.com/favicon.ico","http://mall.kumanju.com");
        } else if (content.equals("微商城")){
            responseNew("微商城","孩子分房，就找酷漫居","http://www.kumanju.com/favicon.ico","http://wx.kumanju.com");
        }else if (content.equals("帅")){
            responseText("没错，没错，我就是帅，哈哈哈哈……");
        }else {
            responseText("抱歉，我不明白你说什么……");
        }
    }

    @Override
    protected void onImage() {

    }

    @Override
    protected void onVoice() {

    }

    @Override
    protected void onVideo() {

    }

    @Override
    protected void onShortVideo() {

    }

    @Override
    protected void onLocation() {

    }

    @Override
    protected void onLink() {

    }

    @Override
    protected void onUnknown() {
        responseText("无法识别消息类型");
    }

    @Override
    protected void click() {

    }

    @Override
    protected void subscribe() {
        responseImage("mGRzBL_l1BwFs6ZESo6fw4DvF0yypSRmA9iT5nq82F1APOg8-XbDVAJUHL3H56Cd");
    }

    @Override
    protected void unSubscribe() {

    }

    @Override
    protected void scan() {
        this.logger.info("ticket value : " + this.wechatRequest.getTicket());
        responseText("欢迎来到小苹果公众号……");
    }

    @Override
    protected void location() {
        responseText("Your Precision is : " + wechatRequest.getPrecision());
    }

    @Override
    protected void view() {

    }

    @Override
    protected void templateMsgCallback() {

    }

    @Override
    protected void scanCodePush() {

    }

    @Override
    protected void scanCodeWaitMsg() {

    }

    @Override
    protected void picSysPhoto() {

    }

    @Override
    protected void picPhotoOrAlbum() {

    }

    @Override
    protected void picWeixin() {

    }

    @Override
    protected void locationSelect() {

    }

    @Override
    protected void kfCreateSession() {

    }

    @Override
    protected void kfCloseSession() {

    }

    @Override
    protected void kfSwitchSession() {

    }
}
