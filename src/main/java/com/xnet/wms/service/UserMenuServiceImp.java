/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.UserMenu;
import com.xnet.wms.repository.UserMenuRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class UserMenuServiceImp implements UserMenuService {

    @Autowired
    UserMenuRepository menuRepository;

    @Override
    public Collection<UserMenu> findAll() {
        return menuRepository.findAll();
    }

}
