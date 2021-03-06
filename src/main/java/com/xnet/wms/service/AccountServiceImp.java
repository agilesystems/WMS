/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.State;
import com.xnet.wms.entity.User;
import com.xnet.wms.helper.Global;
import com.xnet.wms.repository.AccountRepository;
import java.util.Date;
import java.util.List;
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

    @Autowired
    UserService userService;

    @Autowired StateService stateService;
    @Override
    public Account addNew(Account account, int currentUserId) {

        if (account != null && currentUserId > 0) {
            User currentUser = userService.findById(currentUserId);
            account.setCreatedBy(currentUser);
            account.setBranch(currentUser.getBranch());
            State state =  stateService.findById(account.getCity().getState().getId());
            account.getCity().setState_(state);
            return accountRepository.save(account);
        } else {
            return null;
        }

    }

    @Override
    public boolean delete(int accountId, int currentUserId) {
        if (accountId == 0 || currentUserId == 0) {
            return false;
        } else {
            Account account = findById(accountId);
            User user = userService.findById(currentUserId);
            account.setIsDeleted(true);
            account.setDeletedDate(new Date());
            account.setDeletedBy(user);
            addNew(account, user.getId());

            return (account.isIsDeleted());
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
            return addNew(account, user.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<Account> getAllSuppliersWithKey(String key) {

        return accountRepository.findAllByAccountTypeAndBranch_IdAndKey(Global.ACCOUNT_TYPE_SUPPLIER, key);
    }

    @Override
    public List<Account> getAllCustomersWithKey(String key) {

        return accountRepository.findAllByAccountTypeAndBranch_IdAndKey(Global.ACCOUNT_TYPE_CUSTOMER, key);
    }
}
