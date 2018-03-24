/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.StoreDTO;
import com.xnet.wms.entity.Store;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.BranchService;
import com.xnet.wms.service.StoreService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;
    @Autowired
    UserService userService;

    @GetMapping("/all")
    Collection<StoreDTO> findAll(HttpServletRequest httpServletRequest) {

        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        Collection<StoreDTO> stores = new ArrayList<>();
        storeService.findAllByBranch_Id(currentUser.getBranch().getId()).forEach(store -> {
            stores.add(new StoreDTO(store));

        });
        return stores;
    }

    @GetMapping("/id/{id}")
    StoreDTO findOneByID(@PathVariable("id") int id, HttpServletRequest httpServletRequest) {
        return new StoreDTO(storeService.findById(id));
    }

    @PostMapping("/add")
    StoreDTO addNew(@RequestBody Store store, HttpServletRequest httpServletRequest) {
        User u = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));

        store.setBranch(u.getBranch());

        return new StoreDTO(storeService.addNew(store));
    }
}
