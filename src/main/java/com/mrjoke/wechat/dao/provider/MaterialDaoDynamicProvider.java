package com.mrjoke.wechat.dao.provider;

import com.mrjoke.wechat.domain.Material;
import org.apache.ibatis.jdbc.SQL;

import static com.mrjoke.wechat.utils.Constants.MATERIAL_TABLE;

public class MaterialDaoDynamicProvider {

    public String insertMaterial(Material material){
        return new SQL(){
            {
                INSERT_INTO(MATERIAL_TABLE);
                VALUES("user_id","#{user_id}");
                if (material.getMedia_id() != null && !material.getMedia_id().equals("")){
                    VALUES("media_id","#{media_id}");
                }
                if (material.getImage_name() != null && !material.getImage_name().equals("")){
                    VALUES("image_name","#{image_name}");
                }
                if (material.getCreated_at() != null && !material.getCreated_at().equals("")){
                    VALUES("created_at","#{created_at}");
                }
                if (material.getType() != null && !material.getType().equals("")){
                    VALUES("type","#{type}");
                }
            }
        }.toString();
    }

    public String updateMaterial(Material material){
        return new SQL(){
            {
                UPDATE(MATERIAL_TABLE);
                if (material.getMedia_id() != null){
                    SET("media_id = #{media_id}");
                }
                if (material.getImage_name() != null){
                    SET("image_name = #{image_name}");
                }
                if (material.getCreated_at() != null){
                    SET("created_at = #{created_at}");
                }
                if (material.getType() != null){
                    SET("type = #{type}");
                }
                WHERE(" user_id = #{user_id} ");
            }
        }.toString();
    }
}
