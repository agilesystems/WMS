/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.User;
import com.xnet.wms.repository.UserRepository;
import java.util.Collection;
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

    @Override
    public User save(User user) {
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
//        if (userRepository.findByUsernameAndBranch_Id(user.getUsername(), user.getBranch().getId()) == null) {
//            return userRepository.save(user) != null;
//         
//        } else {
//            return false;
//        }
   return userRepository.save(user) ;
//        if (userRepository.findByUsername(user.getUsername()) == null) {
//            return userRepository.save(user) ;
//        
//        }
//        return null;
    }

    @Override
    public User findByUsernameAndBranchid(String username, Integer branchid) {
        return userRepository.findByUsernameAndBranch_Id(username, branchid);
    }

    @Override
    public User getById(Integer id) {
        if (userRepository.findOne(id).getId() != 0) {
            return userRepository.findOne(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<User> findAll() {
        if (userRepository.findAll() != null) {
            return userRepository.findAll();
        } else {
            return null;
        }

    }

}
