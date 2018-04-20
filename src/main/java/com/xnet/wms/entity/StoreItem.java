/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ramy
 */
@Entity(name = "store_item")
public class StoreItem implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Item item;
    private String barcode;
    @ManyToOne
    private Store store;
    private double price;
    private double discountPercentage;
    private int availableQuantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiryDate;

    public StoreItem() {
    }

    public StoreItem(Item item, Store store, double price, double discountPercentage, int availableQuantity, Date expiryDate) {
        this.id = id;
        this.item = item;
        this.store = store;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.availableQuantity = availableQuantity;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
