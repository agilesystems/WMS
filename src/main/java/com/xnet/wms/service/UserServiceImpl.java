/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.User;
import com.xnet.wms.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User authenticated(String username, String password) {

        User u = userRepository.findByUsernameAndPassword(username,
                DigestUtils.sha1Hex(password)
        );

//        if(u!=null && u.getDeleted()){
//            return null;
//        }
        return u;
    }

    public boolean hasAccess(String username, String url) {

        return true;
//        if (username == null || username.isEmpty()) {
//            return false;
//        }
//        if (url.endsWith("/access-denied.html")) {
//            return true;
//        }
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return false;
//        }
//        for (Menu Menu : user.getMenuCollection()) {
//            if (url.equals(Menu.getUrl())) {
//                return true;
//            }
//        }
//        return false;
    }

}
