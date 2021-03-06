/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String globalId;
    @Column(unique = true)
    private String globalBarcode;
    @Basic(optional = false)
    private String name;
    private String description;
    @ManyToOne()
    private Category category;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlobalBarcode() {
        return globalBarcode;
    }

    public void setGlobalBarcode(String globalBarcode) {
        this.globalBarcode = globalBarcode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
