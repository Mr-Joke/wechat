package com.mrjoke.wechat.service.impl;

import com.mrjoke.wechat.dao.MaterialDao;
import com.mrjoke.wechat.domain.Material;
import com.mrjoke.wechat.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("materialService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class MaterialService implements IMaterialService {
    @Autowired
    private MaterialDao materialDao;

    @Override
    public void save(Material material) {
        materialDao.save(material);
    }

    @Override
    @Transactional(readOnly = true)
    public Material findByUserId(String user_id) {
        return materialDao.selectById(user_id);
    }

    @Override
    public void update(Material material) {
        materialDao.update(material);
    }
}
