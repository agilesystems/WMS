/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Category;
import com.xnet.wms.entity.Item;

/**
 * @author ramy
 */
public class ItemDTO {

    private int id;
    private String barcode;
    private String name;
    private String globalId;
    private String description;
    private Category category;

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        if (item == null) {
            return;
        }
        setBarcode(item.getGlobalBarcode());
        setId(item.getId());
        setName(item.getName());
        setGlobalId(item.getGlobalId());
        setDescription(item.getDescription());
        setCategory(item.getCategory());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
