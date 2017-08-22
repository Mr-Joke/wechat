package com.mrjoke.wechat.service;

import org.sword.wechat4j.token.Token;

public interface IAccessTokenService {
    String token();

    boolean save(Token token);

    String find();
}
