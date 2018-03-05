/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ramy
 */
@Entity(name = "branch")
public class Branch extends BaseEntity {

    private String name;
    private String address;

    @OneToMany(mappedBy = "branch")
    private List<User> usersList;

    @OneToMany(mappedBy = "branch")
    private List<Invoice> invoicesList;

    @OneToOne(mappedBy = "branch")
    private Invoice invoiceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<Invoice> getInvoicesList() {
        return invoicesList;
    }

    public void setInvoicesList(List<Invoice> invoicesList) {
        this.invoicesList = invoicesList;
    }

    public Invoice getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(Invoice invoiceList) {
        this.invoiceList = invoiceList;
    }
    
    

}
