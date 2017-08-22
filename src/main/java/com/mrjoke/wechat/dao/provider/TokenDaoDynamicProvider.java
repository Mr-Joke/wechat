package com.mrjoke.wechat.dao.provider;

import com.mrjoke.wechat.domain.Token;
import com.mrjoke.wechat.utils.Constants;
import org.apache.ibatis.jdbc.SQL;

public class TokenDaoDynamicProvider {

    public String insertToken(final Token token){
        return new SQL(){
            {
                INSERT_INTO(Constants.TOKEN_TABLE);
                if (token.getToken() !=null && !token.getToken().equals("")){
                    VALUES("token","#{token}");
                }
                if (token.getExpires() != 0){
                    VALUES("expires","#{expires}");
                }
            }
        }.toString();
    }

    public String updateToken(final Token token){
        return new SQL(){
            {
                UPDATE(Constants.TOKEN_TABLE);
                if (token.getToken() != null){
                    SET(" token = #{token} ");
                }
                if (token.getExpires() != 0){
                    SET(" expires = #{expires} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
