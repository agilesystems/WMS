/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author ramy
 */
@Entity(name = "invoice_item")
public class InvoiceItem implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private StoreItem storeItem;
    private int quantity;
    private double unitPrice;
    private double discountPercentage;
    private double totalPrice;
    private double totalNetPrice;
    @ManyToOne
    private Invoice invoice;

    public InvoiceItem() {
    }

    public InvoiceItem(StoreItem storeItem, double unitPrice, double discountPercentage, int quantity) {

        setStoreItem(storeItem);
        setUnitPrice(unitPrice);
        setDiscountPercentage(discountPercentage);
        setQuantity(quantity);

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice(getQuantity() * getUnitPrice());
        setTotalNetPrice(getTotalPrice() - (getTotalPrice() * getDiscountPercentage()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreItem getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(StoreItem storeItem) {
        this.storeItem = storeItem;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getTotalNetPrice() {
        return totalNetPrice;
    }

    private void setTotalNetPrice(double totalNetPrice) {
        this.totalNetPrice = totalNetPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
