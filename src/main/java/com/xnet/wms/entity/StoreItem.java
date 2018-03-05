/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ramy
 */
@Entity(name = "store_item")
public class StoreItem {

    @Id
    int id;

    String name;
    @ManyToOne(cascade = CascadeType.ALL)
    Item item;
    @ManyToOne(cascade = CascadeType.ALL)
    Store store;

}
