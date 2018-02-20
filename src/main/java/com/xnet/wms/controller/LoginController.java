/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.UserDto;
import com.xnet.wms.entity.User;
import com.xnet.wms.security.JwtGenerator;
import com.xnet.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 *
 * @author mhdsy
 */
@RestController
@RequestMapping("/")
public class LoginController {
    
    @Autowired
    UserService userService;
    private JwtGenerator jwtGenerator;

    public LoginController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }
 
    @PostMapping("/token")
    public String auth(@RequestBody final User jwtUser){

        return jwtGenerator.generate(jwtUser);
    }

    @GetMapping("/login")
    public UserDto login(@PathParam("username") String username, @PathParam("password") String password){
        UserDto userDto = new UserDto(userService.authenticated(username, password));
        return userDto;
    }
    
}
