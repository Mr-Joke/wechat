package com.mrjoke.wechat.dao;

import com.mrjoke.wechat.dao.provider.TokenDaoDynamicProvider;
import com.mrjoke.wechat.domain.Token;
import com.mrjoke.wechat.utils.Constants;
import org.apache.ibatis.annotations.*;

public interface TokenDao {
    @InsertProvider(type = TokenDaoDynamicProvider.class,method = "insertToken")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void save(Token token);

    @UpdateProvider(type = TokenDaoDynamicProvider.class,method = "updateToken")
    void update(Token token);

    @Delete("DELETE FROM " + Constants.TOKEN_TABLE + " where id = #{id}")
    void remove(@Param("id") int id);

    @Select("SELECT * FROM " + Constants.TOKEN_TABLE + " where id = #{id}")
    Token selectById(@Param("id") int id);

    @Select("SELECT * FROM " + Constants.TOKEN_TABLE + " LIMIT 1 offset 0")
    Token selectFirst();

    @Select("SELECT COUNT(*) FROM " + Constants.TOKEN_TABLE)
    int count();
}
