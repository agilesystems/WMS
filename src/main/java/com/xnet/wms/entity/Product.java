/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xnet.wms.entity;

/**
 *
 * @author ramy
 */
public class Product {
   
    private int id;
    private String name;
    private double price;
    private String x1;
    private String x2;
    private String x;
    private String x4;
    private String x5;
    private String x6;
    private String x7;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    
}
