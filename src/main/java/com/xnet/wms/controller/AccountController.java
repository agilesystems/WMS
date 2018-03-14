/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.AccountDTO;
import com.xnet.wms.service.AccountService;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
