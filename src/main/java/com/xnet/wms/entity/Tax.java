/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author ramy
 */
@Entity(name = "tax")
public class Tax implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double value;
    @Enumerated(EnumType.STRING)
    private TaxValueType type;
    private int settingId;

    public Tax() {
    }

    public Tax(String name, double value, TaxValueType type) {

        this.name = name;
        this.value = value;
        this.type = type;

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TaxValueType getType() {
        return type;
    }

    public void setType(TaxValueType type) {
        this.type = type;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

}
