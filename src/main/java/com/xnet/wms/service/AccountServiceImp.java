/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Account;
import com.xnet.wms.helper.Global;
import com.xnet.wms.repository.AccountRepository;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xnet.wms.entity.User;

/**
 *
 * @author Muhammad
 */
@Service
public class AccountServiceImp implements AccountService {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    UserService userService;
    
    @Override
    public Account save(Account account) {
        
        return accountRepository.save(account);
    }
    
    @Override
    public boolean delete(int accountId, int currentUserId) {
        if (accountId == 0) {
            return false;
        } else {
            Account account = findById(accountId);
            User user = userService.findById(currentUserId);
            account.setIsDeleted(true);
            account.setDeletedDate(new Date());
            account.setDeletedBy(user);
            save(account);
            return true;
        }
        
    }
    
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    
    @Override
    public List<Account> findByName(String name) {
        return accountRepository.findByNameContaining(name);
    }
    
    @Override
    public Account findById(int id) {
        return accountRepository.findOne(id);
    }
    
    @Override
    public List<Account> getAllSuppliers() {
        return accountRepository.findByAccountType_Id(Global.ACCOUNT_TYPE_SUPPLIER);
    }
    
    @Override
    public List<Account> getAllCustomers() {
        return accountRepository.findByAccountType_Id(Global.ACCOUNT_TYPE_CUSTOMER);
    }
    
    @Override
    public Account update(Account account, int currentUserId) {
        User user = userService.findById(currentUserId);
        if (user.getBranch() == account.getBranch()) {
            account.setUpdatedBy(user);
            account.setUpdatedDate(new Date());
            return save(account);
        } else {
            return null;
        }
    }
}
