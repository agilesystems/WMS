/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.*;
import java.io.Serializable;

/**
 * @author ramy
 */
public class InvoiceItemDTO implements Serializable {

    private int id;
    private int storeItemID;
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double discountPercentage;
    private double totalPrice;
    private double totalNetPrice;

    public InvoiceItemDTO() {
    }

    public InvoiceItemDTO(InvoiceItem invoiceItem) {
        setDiscountPercentage(invoiceItem.getDiscountPercentage());
        setId(invoiceItem.getId());
        setItemName(invoiceItem.getStoreItem().getItem().getName());
        setQuantity(invoiceItem.getQuantity());
        setStoreItemID(invoiceItem.getStoreItem().getId());
        setTotalNetPrice(invoiceItem.getTotalNetPrice());
        setTotalPrice(invoiceItem.getTotalPrice());
        setUnitPrice(invoiceItem.getUnitPrice());
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

    public int getStoreItemID() {
        return storeItemID;
    }

    public void setStoreItemID(int storeItemID) {
        this.storeItemID = storeItemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
