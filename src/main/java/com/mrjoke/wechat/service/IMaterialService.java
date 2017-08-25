package com.mrjoke.wechat.service;

import com.mrjoke.wechat.domain.Material;

public interface IMaterialService {
    void save(Material material);

    Material findByUserId(String user_id);

    void update(Material material);
}
