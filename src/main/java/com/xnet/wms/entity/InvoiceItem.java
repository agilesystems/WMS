/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ramy
 */
@Entity(name = "invoice_item")
public class InvoiceItem implements Serializable {

    @Id
    int id;
    @ManyToOne
    StoreItem item;
    @ManyToOne
    Invoice invoice;
    int quantity;

    public InvoiceItem() {
    }

    public InvoiceItem(StoreItem item, int quantity) {

        this.item = item;
        this.quantity = quantity;
    }

}
