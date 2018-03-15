/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author ramy
 */
@Entity(name = "discount_type")
public class DiscountType {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String name;

    public DiscountType(String name) {
        setName(name);
    }

    public DiscountType() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
