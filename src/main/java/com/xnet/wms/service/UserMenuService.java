/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.UserMenu;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public interface UserMenuService {

    Collection<UserMenu> findAll();
}
