/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "branch")
public class Branch extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "branch")
    private List<Account> accountsList;

    @OneToMany(mappedBy = "branch")
    private List<Store> storesList;

    @Column(unique = true, nullable = false)
    private String name;
    private String address;

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

}
