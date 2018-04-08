/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Account;
import com.xnet.wms.entity.AccountType;
import com.xnet.wms.entity.Branch;
import com.xnet.wms.entity.City;
import com.xnet.wms.entity.Country;
import com.xnet.wms.entity.State;
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
    private String mobile1;
    private String mobile2;
    private String mobile3;
    private String phone2;
    private String phone3;
    private City city;
    private Branch branch;

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
        setMobile1(account.getMobile1());
        setMobile2(account.getMobile2());
        setMobile3(account.getMobile3());
        setPhone2(account.getPhone2());
        setPhone3(account.getPhone3());
        
        setCity( new City(account.getCity().getId(), account.getCity().getName()));
        
//       setCity(new City(account.getCity().getName(),
//                new State(account.getCity().getState().getName(), 
//                       new Country(account.getCity().getState().getCountry().getName()))));

        //setBranch(new Branch(account.getBranch().getName(), account.getBranch().getAddress()));
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

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getMobile3() {
        return mobile3;
    }

    public void setMobile3(String mobile3) {
        this.mobile3 = mobile3;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
