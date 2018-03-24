/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Menu;
import com.xnet.wms.repository.MenuRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Collection<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu findById(int id) {
        return menuRepository.findOne(id);
    }

    @Override
    public Menu save(Menu menu) {
        if (menu == null) {
            return null;
        }
        return menuRepository.save(menu);
    }

}
