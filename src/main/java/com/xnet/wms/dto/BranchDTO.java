/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Branch;

/**
 *
 * @author ramy
 */
public class BranchDTO {

    private int id;
    private String name;
    private String address;

    public BranchDTO() {
    }

    public BranchDTO(Branch branch) {
        if (branch == null) {
            return;
        }
        setId(branch.getId());
        setName(branch.getName());
        setAddress(branch.getAddress());
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
