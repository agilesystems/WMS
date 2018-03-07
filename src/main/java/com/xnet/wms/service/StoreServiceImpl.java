/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Store;
import com.xnet.wms.repository.StoreRepository;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store addNew(Store store) {
        if (store == null) {
            return null;
        }
        return storeRepository.save(store);
    }

    @Override
    public boolean delete(Store store) {

        if (store == null) {
            return false;
        }
        storeRepository.delete(store);

        return true;
    }

    @Override
    public Collection<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(int id) {
        return storeRepository.findOne(id);
    }

    @Override
    public Collection<Store> findByName(String name) {
        return storeRepository.findAllByNameContaining(name);
    }

}
