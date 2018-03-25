/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.entity.AccountType;
import com.xnet.wms.repository.AccountTypeRepository;
import com.xnet.wms.service.AccountTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ramy
 */
@RestController
@RequestMapping("/account-type")
public class AccountTypeController {

    @Autowired
    AccountTypeService accountTypeService;

    @GetMapping("/all")
    List<AccountType> getAll() {

        return accountTypeService.findAll();
    }

    @GetMapping("/{id}")
    AccountType getByIdl(@PathVariable("id") int id) {

        return accountTypeService.findById(id);
    }
}
