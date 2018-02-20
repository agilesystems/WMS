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
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@CrossOrigin
@RestController
@RequestMapping("/test")
public class testController {
    
    @Autowired
    UserService userService;
    private JwtGenerator jwtGenerator;

    public testController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/test")
    public String test(){
        return "Success!";
    }
    
    @GetMapping("/login")
    public UserDto login(@PathParam("username") String username, @PathParam("password") String password){
       UserDto userDto = new UserDto(userService.authenticated(username, password));
       return userDto;
    }
    @PostMapping("/add")
    public boolean save(@RequestBody User user){
       return userService.save(user);
    }

    @PostMapping("/token")
    public String auth(@RequestBody final User jwtUser){

        return jwtGenerator.generate(jwtUser);
    }
    
}
