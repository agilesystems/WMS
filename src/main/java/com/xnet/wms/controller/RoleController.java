/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.Role;
import com.xnet.wms.service.RoleService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    Collection<Role> getAll() {
        return roleService.getAll();
    }

    @PostMapping("/add")
    Role add(@RequestBody Role role) {
        return roleService.addRole(role);
    }

}
