package com.mrjoke.wechat.service.impl;

import com.mrjoke.wechat.dao.TokenDao;
import com.mrjoke.wechat.dao.impl.TokenDaoJdbcImpl;
import com.mrjoke.wechat.service.IAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sword.wechat4j.token.Token;
import org.sword.wechat4j.token.server.CustomerServer;

@Service("accessTokenService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class AccessTokenService extends CustomerServer implements IAccessTokenService{
//    private TokenDao tokenDao = new TokenDaoJdbcImpl();
    @Autowired
    private TokenDao tokenDao;
    /**
     * 将token串保存到数据库中，如果数据库中存在token串则更新。
     *
     * @param token token串
     * @return 是否成功
     * */
    public boolean save(Token token) {
        if (tokenDao.count() > 0){
            com.mrjoke.wechat.domain.Token firstToken = tokenDao.selectFirst();
            firstToken.setToken(token.getToken());
            firstToken.setExpires(token.getExpires());
            tokenDao.update(firstToken);
        }else {
            com.mrjoke.wechat.domain.Token newToken = new com.mrjoke.wechat.domain.Token();
            newToken.setToken(token.getToken());
            newToken.setExpires(token.getExpires());
            tokenDao.save(newToken);
        }
        return true;
    }

    /**
     * 从数据库中查找token串
     * @return token串
     * */
    @Transactional(readOnly = true)
    public String find() {
        return tokenDao.count() > 0 ? tokenDao.selectFirst().getToken() : null;
    }
}
