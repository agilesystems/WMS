/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.Store;

/**
 *
 * @author ramy
 */
public class StoreDTO {

    private int id;
    private String name;
    private String note;

    public StoreDTO() {
    }

    public StoreDTO(Store store) {
        if (store == null) {
            return;
        }
        setId(store.getId());
        setName(store.getName());
        setNote(store.getNote());
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
