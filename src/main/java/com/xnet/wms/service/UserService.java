/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.User;
import java.util.Collection;

/**
 *
 * @author ramy
 */
public interface UserService {

    User authenticated(String username, String password);

    public boolean hasAccess(String username,String url);
    
    public User save(User user);

    public User findByUsernameAndBranchid(String username, Integer branchid);
    public User findById(Integer id);
    Collection<User> findAll();
}
