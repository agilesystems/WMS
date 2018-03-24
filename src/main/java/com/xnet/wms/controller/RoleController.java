/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.RoleDTO;
import com.xnet.wms.entity.Role;
import com.xnet.wms.service.RoleService;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
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
    Collection<RoleDTO> getAll(HttpServletRequest httpServletRequest) {
               System.out.println(httpServletRequest.getAttribute("userId"));
        Collection<RoleDTO> roles = new ArrayList<>();
        roleService.findAll().forEach(role -> {
            roles.add(new RoleDTO(role));
        });
        return roles;
    }

    @PostMapping("/add")
    RoleDTO add(@RequestBody Role role,HttpServletRequest httpServletRequest) {
        return new RoleDTO(roleService.save(role));
    }

}
