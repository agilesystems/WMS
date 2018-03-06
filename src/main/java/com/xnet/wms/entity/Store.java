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

/**
 *
 * @author ramy
 */
@Entity(name = "store")
public class Store extends BaseEntity {

    private String name;
    private String note;
    @OneToMany(mappedBy = "store")
    private List<StoreItem> storeItemsList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<StoreItem> getStoreItemsList() {
        return storeItemsList;
    }

    public void setStoreItemsList(List<StoreItem> storeItemsList) {
        this.storeItemsList = storeItemsList;
    }
    
    
}
