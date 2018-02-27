/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.UserMenu;
import com.xnet.wms.service.UserMenuService;
import java.util.Collection;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/menu")
public class UserMenuController {

    @Autowired
    UserMenuService menuService;

    @GetMapping("/all")
    Collection<UserMenu> getAll() {
        return menuService.findAll();
    }
}
