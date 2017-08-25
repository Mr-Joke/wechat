package com.mrjoke.wechat.utils;

import com.mrjoke.wechat.service.IAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.sword.wechat4j.token.AccessToken;
/**
 * access_token定时任务类
 **/
public class AccessTokenTask {
    @Autowired
    private IAccessTokenService accessTokenService;

    /**
     * 定时任务，每小时更新access_token
     **/
    public void run(){
        AccessToken accessToken = new AccessToken();
        if (accessToken.request()) {
            System.out.println(accessToken.getToken());
            accessTokenService.save(accessToken);
        }
    }
}
