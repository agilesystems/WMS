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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhammad
 */
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account addNew(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public boolean delete(Account account) {
        account.setIsDeleted(true);
        account.setDeletedDate(new Date());
        accountRepository.save(account);
        return true;
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Collection<Account> findByName(String name) {
        return accountRepository.findByNameContaining(name);
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Collection<Account> getAllSuppliers() {
        return accountRepository.findByAccountType_Id(Global.ACCOUNT_TYPE_SUPPLIER);
    }

    @Override
    public Collection<Account> getAllCustomers() {
        return accountRepository.findByAccountType_Id(Global.ACCOUNT_TYPE_CUSTOMER);
    }

}
