package com.mrjoke.wechat.utils;

import com.mrjoke.wechat.dao.TokenDao;
import com.mrjoke.wechat.service.IAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.sword.wechat4j.token.AccessToken;
import org.sword.wechat4j.token.server.AccessTokenServer;
import org.sword.wechat4j.token.server.CustomerServer;

public class AccessTokenTask {
    @Autowired
    private IAccessTokenService accessTokenService;

    public void run(){
        AccessToken accessToken = new AccessToken();
        if (accessToken.request()) {
            System.out.println(accessToken.getToken());
            accessTokenService.save(accessToken);
        }
    }
}
