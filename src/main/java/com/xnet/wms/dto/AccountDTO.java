/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.AccountType;
import java.util.Date;

/**
 *
 * @author ramy
 */
public class AccountDTO {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String code;
    private String extrainfo;
    private Date createdate;
    private AccountType type;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        if (account == null) {
            return;
        }
        setId(account.getId());
        setName(account.getName());
        setPhone(account.getPhone1());
        setEmail(account.getEmail());
        setAddress(account.getAddress());
        setCode(account.getCode());
        setExtrainfo(account.getNote());
//        setCreatedate(account.getCreateDate());
        setType(new AccountType(account.getAccountType().getName()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExtrainfo() {
        return extrainfo;
    }

    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

}
