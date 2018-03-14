/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.StoreDTO;
import com.xnet.wms.entity.Store;
import com.xnet.wms.service.BranchService;
import com.xnet.wms.service.StoreService;
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
    BranchService branchService;

    @GetMapping("/all")
    Collection<StoreDTO> findAll(HttpServletRequest httpServletRequest) {
        int branchId = Integer.parseInt(httpServletRequest.getAttribute("branchId").toString());
        Collection<StoreDTO> stores = new ArrayList<>();
        storeService.findAllByBranch_Id(branchId).forEach(store -> {
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

        store.setBranch(branchService.findByID(
                Integer.parseInt(httpServletRequest.getAttribute("branchId").toString())
        ));
        return new StoreDTO(storeService.addNew(store));
    }
}
