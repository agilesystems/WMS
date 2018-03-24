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
import com.xnet.wms.service.BranchService;
import com.xnet.wms.service.MenuService;
import com.xnet.wms.service.RoleService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
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
    BranchService branchService;

    @PostMapping("/add")
    public User addNew(@RequestBody User user, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        user.setBranch(currentUser.getBranch());
        return userService.save(user);

    }

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable("id") Integer id, HttpServletRequest httpServletRequest) {
        if (userService.findById(id) != null) {
            return new UserDTO(userService.findById(id));
        } else {
            return null;
        }

    }

}
