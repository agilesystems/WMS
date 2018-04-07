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
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "item_transfer")
public class ItemTransfer implements Serializable {

    @Id
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    Item item;
    @ManyToOne(cascade = CascadeType.ALL)
    Store fromStor;
    @ManyToOne(cascade = CascadeType.ALL)
    Store toStroe;

}
