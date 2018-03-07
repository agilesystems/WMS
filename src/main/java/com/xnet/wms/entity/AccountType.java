/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "account_type")
public class AccountType  extends BaseEntity{
    
    private String name;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accountsList;

    public AccountType() {
    }

    public AccountType(Integer id, String name) {
        setId(id);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }
    
}
