/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.UserDTO;
import com.xnet.wms.entity.Branch;
import com.xnet.wms.entity.Menu;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.MenuService;
import com.xnet.wms.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import javax.websocket.server.PathParam;
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
    MenuService menuService;

    @PostMapping("/add")
    public User add(@RequestBody User user) {

        Collection<Menu> mc = new ArrayList<>();

//        for (Menu m : user.getMenuCollection()) {
//            mc.add(menuService.findById(m.getId()));
//        }
//
//        user.setMenuCollection(mc);
        return userService.save(user);
//        if (userService.save(user)!=null) {
//            return user;
////            return "User Added Successfully";
//        } else {
//            return user;
////            return "Wrong to add user";
//        }

    }

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable("id") Integer id) {
        if (userService.getById(id) != null) {
            return new UserDTO(userService.getById(id));
        } else {
            return null;
        }

    }

}
