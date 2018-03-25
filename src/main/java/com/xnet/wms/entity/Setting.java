/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author ramy
 */
@Entity(name = "setting")
public class Setting {

    @Id
    @GeneratedValue
    private int id;
    private String compnayName;
    private String mobile1;
    private String mobile2;
    private String phone1;
    private String phone2;
    private String email;
    private String address;

    @OneToMany
    @JoinColumn(name = "settingId",referencedColumnName = "id")
    private List<Tax> taxesList;

    public Setting() {
    }

    public Setting(String compnayName, String mobile1, String mobile2, String phone1, String phone2, String email, String address, List<Tax> taxesList) {

        this.compnayName = compnayName;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.taxesList = taxesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompnayName() {
        return compnayName;
    }

    public void setCompnayName(String compnayName) {
        this.compnayName = compnayName;
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
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

    public List<Tax> getTaxesList() {
        return taxesList;
    }

    public void setTaxesList(List<Tax> taxesList) {
        this.taxesList = taxesList;
    }

}
