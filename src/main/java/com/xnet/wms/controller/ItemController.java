/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.ItemDTO;
import com.xnet.wms.dto.StoreItemDTO;
import com.xnet.wms.entity.StoreItem;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.ItemService;
import com.xnet.wms.service.StoreItemService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    StoreItemService storeItemService;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    Collection<ItemDTO> getAll(HttpServletRequest httpServletRequest) {
        Collection<ItemDTO> items = new ArrayList<>();
        itemService.findAll().forEach(i -> {
            items.add(new ItemDTO(i));
        });
        return items;
    }

    @GetMapping("/{key}")
    List<StoreItemDTO> getItemsByKey(HttpServletRequest httpServletRequest, @PathVariable("key") String key) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
//        User currentUser = userService.findById(1);
        List<StoreItem> items = new ArrayList<>();
        if (key.equals("*")) {
            items = storeItemService.findAllByBranchId(currentUser.getBranch().getId());
        } else {
            items = storeItemService.findAllByBranchIdAndKey(currentUser.getBranch().getId(), key);
        }

        List<StoreItemDTO> storeItemDTOs = new ArrayList<>();
        items.forEach(i -> {
            storeItemDTOs.add(new StoreItemDTO(i));
        });
        return storeItemDTOs;

    }
}
