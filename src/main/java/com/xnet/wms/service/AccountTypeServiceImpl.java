/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.service;

import com.xnet.wms.entity.AccountType;
import com.xnet.wms.repository.AccountTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ramy
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType save(AccountType accountType) {

        return accountTypeRepository.save(accountType);
    }

    @Override
    public AccountType findById(int id) {
        return accountTypeRepository.findOne(id);
    }

    @Override
    public List<AccountType> findAll() {
        return accountTypeRepository.findAll();
    }

}
