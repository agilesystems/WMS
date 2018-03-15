/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.AccountDTO;
import com.xnet.wms.entity.Account;
import com.xnet.wms.service.AccountService;
import com.xnet.wms.service.UserService;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/customer/all")
    public Collection<AccountDTO> getAllCustomers(HttpServletRequest httpServletRequest) {

        Collection<AccountDTO> customers = new ArrayList<>();
        accountService.getAllCustomers().forEach(acc -> {
            customers.add(new AccountDTO(acc));
        });
        return customers;
    }

    @GetMapping("/supplier/all")
    public Collection<AccountDTO> getAllSuppliers(HttpServletRequest httpServletRequest) {
        Collection<AccountDTO> suppliers = new ArrayList<>();
        accountService.getAllSuppliers().forEach(acc -> {
            suppliers.add(new AccountDTO(acc));
        });
        return suppliers;
    }

    @GetMapping("/all")
    public Collection<AccountDTO> getAll(HttpServletRequest httpServletRequest) {

        Collection<AccountDTO> customers = new ArrayList<>();
        accountService.getAllCustomers().forEach(acc -> {
            customers.add(new AccountDTO(acc));
        });
        return customers;
    }

    @PostMapping("/add")
    AccountDTO addNew(@RequestBody Account account, HttpServletRequest httpServletRequest) {
        account.setCreatedBy(userService.findById(Integer.parseInt(httpServletRequest.getAttribute("userId").toString())));

        return new AccountDTO(accountService.addNew(account));
    }
}
