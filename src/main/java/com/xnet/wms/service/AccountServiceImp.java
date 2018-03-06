/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.AccountType;
import com.xnet.wms.entity.User;
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
    public boolean save(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(int id, String name, String phone, String email, String address, AccountType type, String Code, String extrainfo, User createdby, Date createdat, User updatedby, Date updatedat, boolean deleted, User deletedby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Account> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Account> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
