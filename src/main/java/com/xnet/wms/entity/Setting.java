/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String tax1Name;
    private double tax1Value;
    private String tax2Name;
    private double tax2Value;
    private String tax3Name;
    private double tax3Value;
    private String tax4Name;
    private double tax4Value;
    private String tax5Name;
    private double tax5Value;
    private String discount1Name;
    private double discount1Valu;
    private String discount2Name;
    private double discount2Valu;

    public Setting() {
    }

    public Setting(String compnayName, String mobile1, String mobile2, String phone1, String phone2, String email, String address) {

        this.compnayName = compnayName;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
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

    public String getTax1Name() {
        return tax1Name;
    }

    public void setTax1Name(String tax1Name) {
        this.tax1Name = tax1Name;
    }

    public double getTax1Value() {
        return tax1Value;
    }

    public void setTax1Value(double tax1Value) {
        this.tax1Value = tax1Value;
    }

    public String getTax2Name() {
        return tax2Name;
    }

    public void setTax2Name(String tax2Name) {
        this.tax2Name = tax2Name;
    }

    public double getTax2Value() {
        return tax2Value;
    }

    public void setTax2Value(double tax2Value) {
        this.tax2Value = tax2Value;
    }

    public String getTax3Name() {
        return tax3Name;
    }

    public void setTax3Name(String tax3Name) {
        this.tax3Name = tax3Name;
    }

    public double getTax3Value() {
        return tax3Value;
    }

    public void setTax3Value(double tax3Value) {
        this.tax3Value = tax3Value;
    }

    public String getTax4Name() {
        return tax4Name;
    }

    public void setTax4Name(String tax4Name) {
        this.tax4Name = tax4Name;
    }

    public double getTax4Value() {
        return tax4Value;
    }

    public void setTax4Value(double tax4Value) {
        this.tax4Value = tax4Value;
    }

    public String getTax5Name() {
        return tax5Name;
    }

    public void setTax5Name(String tax5Name) {
        this.tax5Name = tax5Name;
    }

    public double getTax5Value() {
        return tax5Value;
    }

    public void setTax5Value(double tax5Value) {
        this.tax5Value = tax5Value;
    }

    public String getDiscount1Name() {
        return discount1Name;
    }

    public void setDiscount1Name(String discount1Name) {
        this.discount1Name = discount1Name;
    }

    public double getDiscount1Valu() {
        return discount1Valu;
    }

    public void setDiscount1Valu(double discount1Valu) {
        this.discount1Valu = discount1Valu;
    }

    public String getDiscount2Name() {
        return discount2Name;
    }

    public void setDiscount2Name(String discount2Name) {
        this.discount2Name = discount2Name;
    }

    public double getDiscount2Valu() {
        return discount2Valu;
    }

    public void setDiscount2Valu(double discount2Valu) {
        this.discount2Valu = discount2Valu;
    }

}
