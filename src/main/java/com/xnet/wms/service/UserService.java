/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.User;

/**
 *
 * @author ramy
 */
public interface UserService {

    User authenticated(String username, String password);

    public boolean hasAccess(String username,String url);
}
