/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.controller;

import com.xnet.wms.dto.AccountDTO;
import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.User;
import com.xnet.wms.service.AccountService;
import com.xnet.wms.service.UserService;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

//    @GetMapping("/all")
//    public Collection<AccountDTO> getAll(HttpServletRequest httpServletRequest) {
//        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
//        Collection<AccountDTO> customers = new ArrayList<>();
//        accountService.getAllCustomers().forEach(acc -> {
//            customers.add(new AccountDTO(acc));
//        });
//        return customers;
//    }
    @PostMapping("/add")
    AccountDTO addNew(@RequestBody Account account, HttpServletRequest httpServletRequest) {
        User currentUser = userService.findById(Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        account.setCreatedBy(currentUser);
        return new AccountDTO(accountService.save(account));
    }

    @PostMapping("/update")
    AccountDTO updateAccount(@RequestBody Account account, HttpServletRequest httpServletRequest) {
        try {
            return new AccountDTO(accountService.update(account, Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString())));
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/delete/{id}")
    public boolean deleteAccount(@RequestBody int id, HttpServletRequest httpServletRequest) {
        try {
            return accountService.delete(id, Integer.parseInt(((Claims) httpServletRequest.getAttribute("claims")).get("userId").toString()));
        } catch (Exception e) {
            return false;
        }
    }

}
