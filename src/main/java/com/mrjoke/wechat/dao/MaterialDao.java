package com.mrjoke.wechat.dao;

import com.mrjoke.wechat.dao.provider.MaterialDaoDynamicProvider;
import com.mrjoke.wechat.domain.Material;
import com.mrjoke.wechat.utils.Constants;
import org.apache.ibatis.annotations.*;

public interface MaterialDao {
    @InsertProvider(type = MaterialDaoDynamicProvider.class,method = "insertMaterial")
    void save(Material material);

    @Delete("DELETE FROM " + Constants.MATERIAL_TABLE + " WHERE user_id = #{user_id}")
    void delete(@Param("user_id") String user_id);

    @UpdateProvider(type = MaterialDaoDynamicProvider.class,method = "updateMaterial")
    void update(Material material);

    @Select("SELECT * FROM " + Constants.MATERIAL_TABLE + " WHERE user_id = #{user_id}")
    Material selectById(@Param("user_id") String user_id);
}
