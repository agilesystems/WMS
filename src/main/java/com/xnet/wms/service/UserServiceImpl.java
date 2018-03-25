/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.User;
import com.xnet.wms.repository.UserRepository;
import java.util.Collection;
import java.util.Random;
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

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
//            Random r = new Random();
//            int pass = r.nextInt(999999 - 111111) + 111111;
//            user.setPassword(pass + "");
//            System.out.println("<<<<<<<<Password:" + pass + ">>>>>>>>");
            user.setPassword("123456");
        }
        user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
        userRepository.save(user);
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(generateUsername(user.getBranch().getId(), user.getId()));
            userRepository.save(user);
        }
        System.out.println("<<<<<<<<Username:" + user.getUsername() + ">>>>>>>>");

        return user;
    }

    @Override
    public User findByUsernameAndBranchid(String username, Integer branchid) {
        return userRepository.findByUsernameAndBranch_Id(username, branchid);
    }

    @Override
    public User findById(Integer id) {
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

    private String generateUsername(int branchId, int userId) {

        if (branchId <= 0 || userId <= 0) {
            return null;
        }
        if (branchId < 10) {
            branchId *= 100000;
        } else if (branchId < 100) {
            branchId *= 10000;
        }
        return (String.valueOf(branchId + userId));

    }

}
