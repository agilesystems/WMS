/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.MenuDTO;
import com.xnet.wms.entity.Menu;
import com.xnet.wms.service.MenuService;
import java.util.ArrayList;
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
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/all")
    Collection<MenuDTO> getAll() {

        Collection<MenuDTO> menus = new ArrayList<MenuDTO>();
        menuService.findAll().forEach(m -> {
            menus.add(new MenuDTO(m));
        });
        return menus;
    }
}
