/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "branch")
public class Branch extends BaseEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private String name;
    private String address;
    private String phone;

    public Branch(String name, String address) {
        setAddress(address);
        setName(name);
    }

    public Branch() {

    }

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
