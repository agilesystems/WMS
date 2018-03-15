/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.DiscountType;
import com.xnet.wms.service.DiscountTypeService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 *
 */
@RestController
@RequestMapping("/discount-type")
public class DiscountTypeController {

    @Autowired
    DiscountTypeService discountTypeService;

    @GetMapping("/all")
    Collection<DiscountType> getAll(HttpServletRequest httpRequest) {
        return discountTypeService.findAll();
    }
    
    @PostMapping("/add")
    DiscountType addNew(@RequestBody DiscountType discountType, HttpServletRequest httpRequest ){
        
        return discountTypeService.addNew(discountType);
    }
    
}
