/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.dto;

import com.xnet.wms.entity.StoreItem;
import java.util.Date;

/**
 *
 * @author ramy
 */
public class StoreItemDTO {

    private int itemId;
    private int storeItemId;
    private String name;
    private String itemGlobalId;
    private String globalBarcode;
    private String barcode;
    private String storeName;
    private int storeId;
    private double price;
    private double discountPercentage;
    private int availableQuantity;
    private Date expiryDate;

    public StoreItemDTO() {
    }

    public StoreItemDTO(StoreItem storeItem) {
        setAvailableQuantity(storeItem.getAvailableQuantity());
        setBarcode(storeItem.getBarcode());
        setDiscountPercentage(storeItem.getDiscountPercentage());
        setExpiryDate(storeItem.getExpiryDate());
        setGlobalBarcode(storeItem.getItem().getGlobalBarcode());
        setItemGlobalId(storeItem.getItem().getGlobalId());
        setItemId(storeItem.getItem().getId());
        setName(storeItem.getItem().getName());
        setPrice(storeItem.getPrice());
        setStoreId(storeItem.getStore().getId());
        setStoreItemId(storeItem.getId());
        setStoreName(storeItem.getStore().getName());

    }

    public int getStoreItemId() {
        return storeItemId;
    }

    public void setStoreItemId(int storeItemId) {
        this.storeItemId = storeItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemGlobalId() {
        return itemGlobalId;
    }

    public void setItemGlobalId(String itemGlobalId) {
        this.itemGlobalId = itemGlobalId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public String getGlobalBarcode() {
        return globalBarcode;
    }

    public void setGlobalBarcode(String globalBarcode) {
        this.globalBarcode = globalBarcode;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
