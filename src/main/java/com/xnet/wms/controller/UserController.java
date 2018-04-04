/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.UserDTO;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.BranchService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import javax.servlet.http.HttpServletRequest;
import jdk.nashorn.internal.runtime.Context;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Muhammad
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    BranchService branchService;
    
    @PostMapping("/add")
    public UserDTO addNew(@RequestBody User user, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        user.setBranch(currentUser.getBranch());
        user.setCreatedBy(currentUser);
        
        return new UserDTO(userService.save(user));
    }
    
    @PostMapping("/update")
    public UserDTO updateUser(@RequestBody User user, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        
        user.setUpdatedBy(currentUser);
        user.setUpdatedDate(new Date());
        return new UserDTO(userService.save(user));
    }
    
    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
        if (userService.findById(id) != null) {
            return new UserDTO(userService.findById(id));
        } else {
            return null;
        }
        
    }
    
    @GetMapping("/all")
    public List<UserDTO> getAll(HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        if (currentUser == null) {
            return null;
        }
        List<UserDTO> users = new ArrayList<>();
        userService.findByBranchid(currentUser.getBranch().getId()).forEach((user) -> {
            users.add(new UserDTO(user));
        });
        return users;
    }

//    @GetMapping("/all")
//    public List<HashMap<String, String>> getAllusers(HttpServletRequest httpServletRequest) {
//        List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
//
//        HashMap<String, String> user;
//        for (User u : userService.findAll()) {
//            user = new HashMap<String, String>();
//            user.put("id", u.getId() + "");
//            user.put("fisrtName", u.getFirstName());
//            user.put("lastName", u.getLastName());
//            user.put("phone", u.getPhone());
//            user.put("address", u.getAddress());
//            user.put("userName", u.getUsername());
//            user.put("password", u.getPassword());
//            user.put("rolName", u.getRole().getName());
//            users.add(user);
//        }
//
//        return users;
//    }
}
